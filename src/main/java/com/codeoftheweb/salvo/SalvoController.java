package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @Autowired
    private SalvoRepository salvoRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private PlayerRepository playerRepository;



    @RequestMapping(path="/games",method = RequestMethod.GET )
    private Map<String, Object> AO(Authentication authentication) {
        Map<String, Object> dto = new LinkedHashMap<>();
        if (authentication != null) {
            dto.put("player", makePlayerDTO(playerRepository.findByUserName(authentication.getName())));
            dto.put("game", gameRepository
                    .findAll()
                    .stream()
                    .map(game -> makeGameDTO(game))
                    .collect(toList()));
        }
            else
            {
                dto.put("game", gameRepository
                        .findAll()
                        .stream()
                        .map(game -> makeGameDTO(game))
                        .collect(toList()));
            }
            return dto;
        }

        @RequestMapping(path="/games", method = RequestMethod.POST)
        public ResponseEntity<Map<String, Object>> createUser(Authentication authentication) {
            if (authentication == null) {
                return new ResponseEntity<>(makeMap("error","log in"),HttpStatus.UNAUTHORIZED);
            } else {
                Game game = gameRepository.save(new Game());
                GamePlayer gamePlayer = gamePlayerRepository.save(new GamePlayer(game, currentUser(authentication)));

            return new ResponseEntity<>(makeMap("gamePlayerCreated", gamePlayer.getId()), HttpStatus.CREATED);
            }
        }

        @RequestMapping(path="game/{id}/players" , method = RequestMethod.POST)
        public ResponseEntity<Map<String,Object>> joinGame(@PathVariable long id, Authentication authentication){

        Player currentUser = playerRepository.findByUserName(authentication.getName());
        Game game = gameRepository.findOne(id);

            if(currentUser == null){
                return new ResponseEntity<>(makeMap("error", "Username already exists"), HttpStatus.UNAUTHORIZED);
            }
            if(game == null){
                return new ResponseEntity<>(makeMap("error","No such game"),HttpStatus.FORBIDDEN);
            }

            if(game.getGameplayers().size() > 1){
                  return new ResponseEntity<>(makeMap("error","Game is Full"),HttpStatus.FORBIDDEN);
            }

            GamePlayer gamePlayer = new GamePlayer(game,currentUser);
            gamePlayerRepository.save(gamePlayer);
            return new ResponseEntity<>(makeMap("gpId", gamePlayer.getId()),HttpStatus.CREATED);
    }

    private Player currentUser (Authentication authentication){
        if (authentication != null) {
            return playerRepository.findByUserName(authentication.getName());
        } else {
            return null;
        }
    }

    @RequestMapping(path="/players",method =  RequestMethod.POST)
    public ResponseEntity<Object> getNewPlayer(@RequestParam String userName , String password) {

        if (userName.isEmpty()) {
            return new ResponseEntity<>("No name given", HttpStatus.FORBIDDEN);
        }

        Player player = playerRepository.findByUserName(userName);
        if (player != null) {
            return new ResponseEntity<>("Name already used", HttpStatus.CONFLICT);
        } else {
            playerRepository.save(new Player(userName, password));
            return new ResponseEntity<>("Named added", HttpStatus.CREATED);
        }

    }
 @Autowired ShipRepository shipRepository ;

    @RequestMapping(path="/games/players/{gamePlayerId}/ships",method = RequestMethod.POST)
    public ResponseEntity<Map<String ,Object>> getShips(@PathVariable Long gamePlayerId ,
                                                        Authentication authentication,
                                                        @RequestBody Set<Ship> ships) {
        Player currentUser = playerRepository.findByUserName(authentication.getName());

        if (currentUser != null) {
            GamePlayer gamePlayer = gamePlayerRepository.findOne(gamePlayerId);
            if (gamePlayer != null) {
                if (currentUser.getUserName() == gamePlayer.getPlayer().getUserName()) ;
                if (gamePlayer.getShips().size() == 0) {
                    for (Ship ship : ships) {
                        ship.setGameplayer(gamePlayer);
                        shipRepository.save(ship);
                    }
                    return new ResponseEntity<>(makeMap("success", "shipsCreated"), HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(makeMap("success", "ships already placed"), HttpStatus.FORBIDDEN);
                }
            }else {
                return new ResponseEntity<>(makeMap("success","no name found"), HttpStatus.UNAUTHORIZED);
            }
        }else {
            return new ResponseEntity<>(makeMap("error","logged in"), HttpStatus.UNAUTHORIZED);
        }
    }


    private Integer getTurn(GamePlayer gamePlayer){
        Integer lastTurn = 0;
        for (Salvo salvo : gamePlayer.getSalvoes()) {
            Integer turn = salvo.getTurnNumber();
            if (lastTurn < turn) {
                lastTurn = turn;
            }
        }
        return lastTurn + 1;
    }

    @RequestMapping(path="/games/players/{gamePlayerId}/salvos",method = RequestMethod.POST)
    public ResponseEntity<Map<String ,Object>> getSalvos(@PathVariable Long gamePlayerId,
                                                         Authentication authentication,
                                                         @RequestBody Salvo salvo) {
        Player currentUser = playerRepository.findByUserName(authentication.getName());

        if (currentUser != null) {
            GamePlayer gamePlayer = gamePlayerRepository.findOne(gamePlayerId);
            if (gamePlayer != null) {
                if (currentUser.getUserName() == gamePlayer.getPlayer().getUserName()) {
                        salvo.setGameplayer(gamePlayer);
                        salvo.setTurnNumber(getTurn(gamePlayer));
                        salvoRepository.save(salvo);

                    return new ResponseEntity<>(makeMap("success", "salvoCreated"), HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>(makeMap("error", "salvo already placed"), HttpStatus.FORBIDDEN);
                }
            }else {
                return new ResponseEntity<>(makeMap("error","no name found"), HttpStatus.UNAUTHORIZED);
            }
        }else{
            return new ResponseEntity<>(makeMap("error","user not logged in"), HttpStatus.UNAUTHORIZED);
        }
    }


    public List<Object> getGamesId() {
        return
         gameRepository
                .findAll()
                .stream()
                .map(game -> makeGameDTO(game))
                .collect(toList());
    }

    @RequestMapping("/game_view/{id}")
    public ResponseEntity< Map<String, Object>> findGamePlayerID (@PathVariable Long id, Authentication authentication) {
        GamePlayer gamePlayer = gamePlayerRepository.findOne(id);

        Player player = gamePlayer.getPlayer();
        Player currentUser = playerRepository.findByUserName(authentication.getName());

        if (player.getId() == currentUser.getId()) {


            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("games", makeGameDTO(gamePlayer.getGame()));
            objectMap.put("ships", gamePlayer.getShips().stream()
                    .map(ship -> makeShipDTO(ship))
                    .collect(toList()));

            objectMap.put("salvo", gamePlayer.getSalvoes().stream()
                    .map(salvo -> makeSalvoDTO(salvo))
                    .collect(toList()));
            if (gamePlayer.getGame().getGameplayers().size() != 1) {
                GamePlayer otherPlayer = getOtherPlayer(gamePlayer);
                objectMap.put("OtherSalvo", otherPlayer.getSalvoes().stream()
                        .map(salvo -> makeSalvoDTO(salvo))
                        .collect(toList()));
            }
            return new ResponseEntity<>(makeMap("gamesObject",objectMap), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(makeMap("error","logIn"), HttpStatus.UNAUTHORIZED);
        }
    }
    private Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    @RequestMapping("/leaderboard")
    public List<Object> getLeaderboard() {
        List<Object> list = new ArrayList<>();
        List<Player> players = playerRepository.findAll();
        for (Player player : players) {
            Map<String, Object> map = new LinkedHashMap<>();
            Double total = 0.0;
            Integer win = 0;
            Integer lose = 0;
            Integer tie = 0;
            Set<Score> scores = player.getScores();
            for (Score score : scores) {
                total += score.getScore();
                if (score.getScore() == 1) {
                    win++;
                }
                if (score.getScore() > 0 && score.getScore() < 1) {
                    tie++;
                }
                if (score.getScore() == 0) {
                    lose++;
                }
            }
            map.put("player", player.getUserName());
            map.put("total", total);
            map.put("win", win);
            map.put("tie", tie);
            map.put("lose", lose);
            list.add(map);
        }
        return list;
    }



    public GamePlayer getOtherPlayer(GamePlayer gamePlayer){
        List<GamePlayer> gamePlayersList = new ArrayList<>();
        Set<GamePlayer> gamePlayerSet = gamePlayer.getGame().getGameplayers();
        for (GamePlayer gp : gamePlayerSet) {
            if (gp != gamePlayer) {
                gamePlayersList.add(gp);
            }
        }
        return gamePlayersList.get(0);
    }


    private Map<String, Object> makeGameDTO(Game game) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", game.getId());
        dto.put("creation", game.getStartDate());
        dto.put("gamePlayers", game.getGameplayers()
                .stream()
                .map(gamePlayer -> makeGamePlayerDTO(gamePlayer))
                .collect(toList()));
        return dto;
    }

    private Map<String, Object> makeGamePlayerDTO(GamePlayer gamePlayer) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", gamePlayer.getId());
        dto.put("player", makePlayerDTO(gamePlayer.getPlayer()));
        if(gamePlayer.getScore() != null ){
            dto.put("score",makeScoreDTO(gamePlayer.getScore()));
        }
        return dto;
    }
    private Map<String, Object>makePlayerDTO(Player player) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("playerId", player.getId());
        dto.put("player_email",player.getUserName());
        return dto;
    }


    private Map<String, Object> makeShipDTO(Ship ship) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("ships", ship.getType());
        dto.put("locations", ship.getLocations());
        return dto;

    }
        private Map<String, Object> makeSalvoDTO(Salvo salvo){
            Map<String, Object> dto = new LinkedHashMap<>();
            dto.put("turn", salvo.getTurnNumber());
            dto.put("player" , salvo.getGameplayer().getId());
            dto.put("locations", salvo.getLocations());
            return dto;
        }

            private Map<String, Object> makeScoreDTO(Score player) {
               Map<String, Object> dto = new LinkedHashMap<>();
               dto.put("scores",player.getScore());
               return dto;
    }

}
