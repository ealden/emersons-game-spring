package com.escanan.ealden.race.controller.api.model;

import com.escanan.ealden.race.model.Racer;

import java.util.List;

public class Race {
    private List<Racer> racers;
    private boolean testMode;

    public Race(List<Racer> racers, boolean testMode) {
        this.racers = racers;
        this.testMode = testMode;
    }

    public List<Racer> getRacers() {
        return racers;
    }

    public int getFinishLine() {
        return Racer.DEFAULT_FINISH_LINE;
    }

    public boolean isTestMode() {
        return testMode;
    }
}
