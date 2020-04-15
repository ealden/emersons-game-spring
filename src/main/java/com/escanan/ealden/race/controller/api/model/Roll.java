package com.escanan.ealden.race.controller.api.model;

import com.escanan.ealden.race.model.Racer;

public class Roll {
    private Long id;
    private Racer.SpeedType speedType;
    private Integer roll;

    public Roll(Long id, String speedType, Integer roll) {
        this.id = id;
        this.speedType = Racer.SpeedType.valueOf(speedType);
        this.roll = roll;
    }

    public Long getId() {
        return id;
    }

    public Racer.SpeedType getSpeedType() {
        return speedType;
    }

    public Integer getRoll() {
        return roll;
    }
}
