package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.util.*;


@Entity
public class Game {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private Date startDate;


    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    Set<GamePlayer> gameplayers;

    public void addGamePlayer(GamePlayer gameplayer) {
        gameplayer.setGame(this);
        gameplayers.add(gameplayer);
    }

    public long getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {

        this.startDate = startDate;
    }

    public Game() {
        this.startDate = new Date();
    }

    public Set<GamePlayer> getGameplayers() {
        return gameplayers;
    }

    public void setGameplayers(Set<GamePlayer> gameplayers) {
        this.gameplayers = gameplayers;
    }


}


