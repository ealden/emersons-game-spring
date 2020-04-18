package com.escanan.ealden.race.controller.api.model;

import com.escanan.ealden.race.model.Racer;

import java.util.List;

public class Race {
    private List<Racer> racers;

    public Race(List<Racer> racers) {
        this.racers = racers;
    }

    public List<Racer> getRacers() {
        return racers;
    }

    public int getFinishLine() {
        return Racer.DEFAULT_FINISH_LINE;
    }
}
