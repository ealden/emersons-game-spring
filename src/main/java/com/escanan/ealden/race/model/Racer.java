package com.escanan.ealden.race.model;

import javax.persistence.*;

import static java.lang.Math.max;

@Entity
public class Racer {
    public enum SpeedType {
        NORMAL, SUPER
    }

    public static final int DEFAULT_FINISH_LINE = 15;

    private static final int ZERO_MOVE = 0;
    private static final int ODD_NORMAL_MOVE = 1;
    private static final int EVEN_NORMAL_MOVE = 2;


    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Race race;

    private String name;
    private int position = 0;
    private int damage = 0;
    private int rank = 0;
    private int finishLine = DEFAULT_FINISH_LINE;

    public Racer() {

    }

    public Racer(String name) {
        this.name = name;
    }

    public boolean isOver() {
        return position >= finishLine;
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
        if (isOver()) {
            return finishLine;
        } else {
            return position;
        }
    }

    public int getRank() {
        return rank;
    }

    public int getFinishLine() {
        return finishLine;
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

    public void setFinishLine(int finishLine) {
        this.finishLine = finishLine;
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
