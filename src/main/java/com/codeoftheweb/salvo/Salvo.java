package com.codeoftheweb.salvo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Salvo {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private  String  turn_number;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gameplayer_id")
    private GamePlayer gameplayer;


    @ElementCollection
    @Column(name="Location")
    private List<String> Locations = new ArrayList<>();

   public Salvo() { }


    public Salvo(String turn_number, GamePlayer gameplayer, List<String> locations) {
        this.turn_number = turn_number;
        this.gameplayer = gameplayer;
        this.Locations = locations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTurn_number() {
        return turn_number;
    }

    public void setTurn_number(String turn_number) {
        this.turn_number = turn_number;
    }

    public GamePlayer getGameplayer() {
        return gameplayer;
    }

    public void setGameplayer(GamePlayer gameplayer) {
        this.gameplayer = gameplayer;
    }

    public List<String> getLocations() {
        return Locations;
    }

    public void setLocations(List<String> locations) {
        Locations = locations;
    }
}
