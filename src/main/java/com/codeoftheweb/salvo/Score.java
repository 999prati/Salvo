package com.codeoftheweb.salvo;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Score {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

   private Double score;

    public Score(){}

    public Score(Game game , Player player, Double score){
        this.game = game;
        this.player = player;
        this.score = score;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getplayer() {
        return player;
    }

    public void setplayer(Player player) {
        this.player = player;
    }

    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
        this.score = score;
    }

}
