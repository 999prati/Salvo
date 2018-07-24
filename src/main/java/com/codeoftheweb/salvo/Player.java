package com.codeoftheweb.salvo;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String userName;

    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    Set<GamePlayer> gameplayers;

    public Player() { }

    public Player(String userName) {
        this.userName = userName;
    }

    public void addGamePlayer(GamePlayer gameplayer) {
        gameplayer.setPlayer(this);
        gameplayers.add(gameplayer);
    }

    public List<Game> getGames() {
        return gameplayers.stream().map(sub -> sub.getGame()).collect(toList());
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<GamePlayer> getGameplayers() {
        return gameplayers;
    }

    public void setGameplayers(Set<GamePlayer> gameplayers) {
        this.gameplayers = gameplayers;
    }
}



