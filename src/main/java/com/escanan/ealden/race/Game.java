package com.escanan.ealden.race;

public class Game {
    private Integer position = 0;

    public void move(Integer roll) {
        position += roll;
    }

    public Integer getPosition() {
        return position;
    }
}
