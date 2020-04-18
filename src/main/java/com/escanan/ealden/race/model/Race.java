package com.escanan.ealden.race.model;

import com.escanan.ealden.race.model.Racer.SpeedType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Entity
public class Race {
    private static final int MAX_ROLL = 6;

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy="race", cascade = ALL, fetch = EAGER)
    private List<Racer> racers = new ArrayList<>();

    @ManyToOne
    private Racer currentRacer;

    public Race() {

    }

    public Race(List<Racer> racers) {
        this.racers = racers;
    }

    public Race addRacer(Racer racer) {
        racers.add(racer);

        racer.setRace(this);
        racer.setRank(racers.size());

        if (currentRacer == null) {
            currentRacer = racer;
        }

        return this;
    }

    public void roll(SpeedType speedType) {
        int roll = new Random().nextInt(MAX_ROLL) + 1;

        roll(roll, speedType);
    }

    public void roll(int roll, SpeedType speedType) {
        if (currentRacer != null) {
            currentRacer.roll(roll, speedType);
        }
    }

    public Long getId() {
        return id;
    }

    public List<Racer> getRacers() {
        return racers;
    }

    public Racer getCurrentRacer() {
        return currentRacer;
    }

    public int getFinishLine() {
        return Racer.DEFAULT_FINISH_LINE;
    }
}
