package com.codeoftheweb.salvo;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import sun.security.util.Password;

import java.util.*;
import java.util.stream.Collectors;

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



    @RequestMapping(path="/games", method =  RequestMethod.POST)
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

    @RequestMapping(path = "/players", method = RequestMethod.POST)
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
        GamePlayer otherPlayer = getOtherPlayer(gamePlayer);
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

            objectMap.put("OtherSalvo", otherPlayer.getSalvoes().stream()
                    .map(salvo -> makeSalvoDTO(salvo))
                    .collect(toList()));
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
            dto.put("turn", salvo.getTurn_number());
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
