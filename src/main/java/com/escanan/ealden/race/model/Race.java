package com.escanan.ealden.race.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Entity
public class Race {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy="race", cascade = ALL, fetch = EAGER)
    private List<Racer> racers = new ArrayList<>();

    public Race() {

    }

    public Race(List<Racer> racers) {
        this.racers = racers;
    }

    public Race addRacer(Racer racer) {
        racer.setRace(this);

        racers.add(racer);

        return this;
    }

    public Long getId() {
        return id;
    }

    public List<Racer> getRacers() {
        return racers;
    }

    public int getFinishLine() {
        return Racer.DEFAULT_FINISH_LINE;
    }
}
