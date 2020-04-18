package com.escanan.ealden.race.model;

import javax.persistence.*;

import static java.lang.Math.max;

@Entity
public class Racer {
    public enum SpeedType {
        NORMAL, SUPER
    }

    private static final int ZERO_MOVE = 0;
    private static final int ODD_NORMAL_MOVE = 1;
    private static final int EVEN_NORMAL_MOVE = 2;

    static final int MAX_DAMAGE = 6;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Race race;

    private String name;
    private int position = 0;
    private int damage = 0;
    private int rank = 0;

    public Racer() {

    }

    public Racer(String name) {
        this.name = name;
    }

    public void roll(int roll, SpeedType speedType) {
        if (SpeedType.NORMAL.equals(speedType)) {
            normalRoll(roll);
        } else if (SpeedType.SUPER.equals(speedType)) {
            superRoll(roll);
        }
    }

    public Long getId() {
        return id;
    }

    Race getRace() {
        return race;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getPosition() {
        if (isWinner()) {
            return race.getFinishLine();
        } else {
            return position;
        }
    }

    public int getRank() {
        return rank;
    }

    public boolean isCrashed() {
        return (MAX_DAMAGE <= damage);
    }

    public boolean isWinner() {
        return (position >= race.getFinishLine());
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    private void normalRoll(int roll) {
        roll(normal(roll));
    }

    private void superRoll(int roll) {
        roll(roll);

        incrementDamage();
    }

    private void roll(int roll) {
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
