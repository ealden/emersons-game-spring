package com.escanan.ealden.race.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static com.escanan.ealden.race.model.SpeedType.SUPER;

@Entity
public class Roll {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Race race;

    @ManyToOne
    private Racer racer;

    private int position;
    private int damage;
    private SpeedType speedType;
    private int roll;
    private int move;
    private int newPosition;
    private int newDamage;
    private boolean crashed;
    private boolean win;

    public static Roll beforeRoll(Racer racer, int roll, SpeedType speedType) {
        Roll entry = new Roll();
        entry.setRacer(racer);
        entry.setRace(racer.getRace());
        entry.setPosition(racer.getPosition());
        entry.setDamage(racer.getDamage());
        entry.setSpeedType(speedType);
        entry.setRoll(roll);

        return entry;
    }

    public Long getId() {
        return id;
    }

    @JsonIgnore
    public Race getRace() {
        return race;
    }

    public Racer getRacer() {
        return racer;
    }

    public int getPosition() {
        return position;
    }

    public int getDamage() {
        return damage;
    }

    public SpeedType getSpeedType() {
        return speedType;
    }

    public int getRoll() {
        return roll;
    }

    public int getMove() {
        return move;
    }

    public int getNewPosition() {
        return newPosition;
    }

    public int getNewDamage() {
        return newDamage;
    }

    public boolean isCrashed() {
        return crashed;
    }

    public boolean isWin() {
        return win;
    }

    public boolean isDamaged() {
        return (newDamage > 0);
    }

    public boolean isNormalSpeed() {
        return NORMAL.equals(speedType);
    }

    public boolean isSuperSpeed() {
        return SUPER.equals(speedType);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setSpeedType(SpeedType speedType) {
        this.speedType = speedType;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public void setNewPosition(int newPosition) {
        this.newPosition = newPosition;
    }

    public void setNewDamage(int newDamage) {
        this.newDamage = newDamage;
    }

    public void setCrashed(boolean crashed) {
        this.crashed = crashed;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public void afterRoll(Racer racer) {
        setNewPosition(racer.getPosition());
        setNewDamage(racer.getDamage());
        setCrashed(racer.isCrashed());
        setWin(racer.isWinner());
    }
}
