package com.escanan.ealden.race;

import static java.lang.Math.max;

public class Game {
    public static final int TOTAL_POSITIONS = 10;

    public enum SpeedType {
        NORMAL, SUPER
    }

    private int position = 0;
    private int damage = 0;

    public boolean isOver() {
        return position >= TOTAL_POSITIONS;
    }

    public void move(int roll, SpeedType speedType) {
        if (SpeedType.NORMAL.equals(speedType)) {
            normalMove(roll);
        } else if (SpeedType.SUPER.equals(speedType)) {
            superMove(roll);
        }
    }

    private void normalMove(int roll) {
        var odd = ((roll % 2) == 1);
        var normalRoll = ((odd) ? 1 : 2);

        move(normalRoll);
    }

    private void superMove(int roll) {
        move(roll);

        damage++;
    }

    private void move(int roll) {
        position += max((roll - damage), 0);
    }

    public int getDamage() {
        return damage;
    }

    public int getPosition() {
        if (isOver()) {
            return TOTAL_POSITIONS;
        } else {
            return position;
        }
    }

    public void setDamage(int damage) {
        // TODO: this method should not be public
        this.damage = damage;
    }

    public void setPosition(int position) {
        // TODO: this method should not be public
        this.position = position;
    }
}
