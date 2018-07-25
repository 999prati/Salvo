package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping("/games")
    public List<Object> getGamesId() {
        return
         gameRepository
                .findAll()
                .stream()
                .map(game -> makeGameDTO(game))
                .collect(toList());
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
        return dto;
    }
    private Map<String, Object>makePlayerDTO(Player player) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("playerId", player.getId());
        dto.put("player_email", player.getUserName());
        return dto;
    }
    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @RequestMapping("/game_view/{id}")
    public Map<String, Object> findGamePlayerID (@PathVariable Long id) {
        GamePlayer gamePlayer = gamePlayerRepository.findOne(id);

        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("games", makeGameDTO(gamePlayer.getGame()));
        return objectMap ;
        

    }

}


