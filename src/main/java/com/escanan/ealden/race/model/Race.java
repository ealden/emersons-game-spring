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
    private static final int DEFAULT_FINISH_LINE = 10;

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy="race", cascade = ALL, fetch = EAGER)
    @OrderBy("id")
    private List<Racer> racers = new ArrayList<>();

    @ManyToOne
    private Racer currentRacer;

    private int finishLine = DEFAULT_FINISH_LINE;

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

            nextRacer();
        }
    }

    private void nextRacer() {
        int nextRank = ((currentRacer.getRank() % racers.size()) + 1);

        for (Racer racer : racers) {
            if (nextRank == racer.getRank()) {
                currentRacer = racer;

                break;
            }
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
        return finishLine;
    }

    public boolean isOver() {
        boolean allCrashed = true;

        for (Racer racer : racers) {
            if (racer.isWinner()) {
                return true;
            } else {
                allCrashed = (allCrashed && racer.isCrashed());
            }
        }

        return allCrashed;
    }

    public void setFinishLine(int finishLine) {
        this.finishLine = finishLine;
    }
}
