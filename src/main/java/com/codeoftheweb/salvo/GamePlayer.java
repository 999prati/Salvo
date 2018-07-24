package com.codeoftheweb.salvo;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;


@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private Date startDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    public GamePlayer() { }

    public GamePlayer(Game game, Player player) {
        this.game = game;
        this.player = player;
        this.startDate = startDate;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
