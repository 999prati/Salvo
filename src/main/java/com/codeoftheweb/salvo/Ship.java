package com.codeoftheweb.salvo;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private  String type;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gameplayer_id")
    private GamePlayer gameplayer;

    @ElementCollection
    @Column(name="Location")
    private List<String> locations = new ArrayList<>();


    public Ship() { }

    public Ship(String type, GamePlayer gameplayer, List<String> locations) {
        this.type = type;
        this.gameplayer = gameplayer;
        this.locations = locations;


    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GamePlayer getGameplayer() {
        return gameplayer;
    }

    public void setGameplayer(GamePlayer gameplayer) {
        this.gameplayer = gameplayer;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }
}
