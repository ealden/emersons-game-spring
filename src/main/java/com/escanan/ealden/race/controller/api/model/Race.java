package com.escanan.ealden.race.controller.api.model;

import com.escanan.ealden.race.model.Racer;

public class Race {
    private Iterable<Racer> racers;
    private boolean randomRoll;

    public Race(Iterable<Racer> racers, boolean randomRoll) {
        this.racers = racers;
        this.randomRoll = randomRoll;
    }

    public Iterable<Racer> getRacers() {
        return racers;
    }

    public boolean isRandomRoll() {
        return randomRoll;
    }
}
