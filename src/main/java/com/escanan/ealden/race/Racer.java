package com.escanan.ealden.race;

import static java.lang.Math.max;

public class Racer {
    public enum SpeedType {
        NORMAL, SUPER
    }

    public static final int TOTAL_POSITIONS = 10;

    private static final int ZERO_MOVE = 0;
    private static final int ODD_NORMAL_MOVE = 1;
    private static final int EVEN_NORMAL_MOVE = 2;

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

    private void normalMove(int roll) {
        move(normal(roll));
    }

    private void superMove(int roll) {
        move(roll);

        incrementDamage();
    }

    private void move(int roll) {
        position += max((roll - damage), ZERO_MOVE);
    }

    private int normal(int roll) {
        return (isOdd(roll) ? ODD_NORMAL_MOVE : EVEN_NORMAL_MOVE);
    }

    private boolean isOdd(int roll) {
        return (roll % 2) == 1;
    }

    private void incrementDamage() {
        damage++;
    }
}
