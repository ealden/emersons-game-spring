package com.escanan.ealden.race;

public class Game {
    public enum SpeedType {
        NORMAL, SUPER
    }

    private Integer position = 0;
    private Integer damage = 0;

    public void move(Integer roll, SpeedType speedType) {
        if (SpeedType.NORMAL.equals(speedType)) {
            var odd = ((roll % 2) == 1);

            position += (odd) ? 1 : 2;
        } else if (SpeedType.SUPER.equals(speedType)) {
            position += roll;
            damage++;
        }
    }

    public Integer getDamage() {
        return damage;
    }

    public Integer getPosition() {
        return position;
    }
}
