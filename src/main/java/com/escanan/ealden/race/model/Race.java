package com.escanan.ealden.race.model;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
public class Race {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private List<Racer> racers;

    public Race() {

    }

    public Race(List<Racer> racers) {
        this.racers = racers;
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
