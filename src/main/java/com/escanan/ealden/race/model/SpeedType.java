package com.escanan.ealden.race.model;

public enum SpeedType {
    NORMAL  (1, 2, 1, 0),
    SUPER   (0, 7, 0, 2);

    private int prefix;
    private int modulo;
    private int offset;
    private int damage;

    SpeedType(int prefix, int modulo, int offset, int damage) {
        this.prefix = prefix;
        this.modulo = modulo;
        this.offset = offset;
        this.damage = damage;
    }

    public int calculate(int roll) {
        return (((prefix + roll) % modulo) + offset);
    }
}
