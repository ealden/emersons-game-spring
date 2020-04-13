package com.escanan.ealden.race;

public class Game {
    public static final Integer TOTAL_POSITIONS = 10;

    public enum SpeedType {
        NORMAL, SUPER
    }

    private Integer position = 0;
    private Integer damage = 0;

    public boolean isOver() {
        return position >= TOTAL_POSITIONS;
    }

    public void move(Integer roll, SpeedType speedType) {
        if (SpeedType.NORMAL.equals(speedType)) {
            normalMove(roll);
        } else if (SpeedType.SUPER.equals(speedType)) {
            superMove(roll);
        }
    }

    private void normalMove(Integer roll) {
        var odd = ((roll % 2) == 1);

        var move = ((odd) ? 1 : 2);
        move = move - damage;

        position += ((move > 0) ? move : 0);
    }

    private void superMove(Integer roll) {
        var move = (roll - damage);

        position += ((move > 0) ? move : 0);

        damage++;
    }

    public Integer getDamage() {
        return damage;
    }

    public Integer getPosition() {
        if (isOver()) {
            return TOTAL_POSITIONS;
        } else {
            return position;
        }
    }

    public void setDamage(Integer damage) {
        // TODO: this method should not be public
        this.damage = damage;
    }

    public void setPosition(Integer position) {
        // TODO: this method should not be public
        this.position = position;
    }
}
