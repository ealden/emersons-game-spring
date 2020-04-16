package com.escanan.ealden.race.controller.api.model;

import com.escanan.ealden.race.model.Racer;

import java.util.List;

public class Race {
    private List<Racer> racers;
    private boolean randomRoll;

    public Race(List<Racer> racers, boolean randomRoll) {
        this.racers = racers;
        this.randomRoll = randomRoll;
    }

    public List<Racer> getRacers() {
        return racers;
    }

    public int getFinishLine() {
        return Racer.DEFAULT_FINISH_LINE;
    }

    public boolean isRandomRoll() {
        return randomRoll;
    }
}
