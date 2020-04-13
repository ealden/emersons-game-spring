package com.escanan.ealden.race;

public class Game {
    public enum SpeedType {
        NORMAL, SUPER
    }

    private Integer position = 0;
    private Integer damage = 0;

    public void move(Integer roll, SpeedType speedType) {
        if (SpeedType.NORMAL.equals(speedType)) {
            normalMove(roll);
        } else if (SpeedType.SUPER.equals(speedType)) {
            superMove(roll);
        }
    }

    private void normalMove(Integer roll) {
        var odd = ((roll % 2) == 1);

        position += (odd) ? 1 : 2;
    }

    private void superMove(Integer roll) {
        position += (roll - damage);

        damage++;
    }

    public Integer getDamage() {
        return damage;
    }

    public Integer getPosition() {
        return position;
    }

    public void setDamage(Integer damage) {
        // TODO: this method should not be public
        this.damage = damage;
    }
}
