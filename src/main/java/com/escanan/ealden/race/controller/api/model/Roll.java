package com.escanan.ealden.race.controller.api.model;

import com.escanan.ealden.race.model.Racer.SpeedType;

public class Roll {
    private SpeedType speedType;
    private Integer roll;

    public Roll(String speedType, Integer roll) {
        this.speedType = SpeedType.valueOf(speedType);
        this.roll = roll;
    }

    public SpeedType getSpeedType() {
        return speedType;
    }

    public Integer getRoll() {
        return roll;
    }
}
