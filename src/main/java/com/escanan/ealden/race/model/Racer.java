package com.escanan.ealden.race.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.escanan.ealden.race.model.Roll.createRoll;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Entity
public class Racer {
    public static final int MAX_DAMAGE = 6;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Race race;

    @OneToMany(mappedBy = "racer", cascade = ALL, fetch = EAGER)
    @OrderBy("id")
    private List<Roll> rolls = new ArrayList<>();

    @ManyToOne
    private Roll lastRoll;

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
        int oldPosition = position;
        int oldDamage = damage;

        position += speedType.move(roll, damage);
        damage += speedType.getDamage();

        addRoll(createRoll(this, oldPosition, oldDamage, roll, speedType));
    }

    private Racer addRoll(Roll roll) {
        rolls.add(roll);
        lastRoll = roll;

        return this;
    }

    public Long getId() {
        return id;
    }

    @JsonIgnore
    public Race getRace() {
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

    @JsonIgnore
    public Roll getLastRoll() {
        return lastRoll;
    }

    public boolean isCrashed() {
        return (MAX_DAMAGE <= damage);
    }

    public boolean isWinner() {
        return (position >= race.getFinishLine());
    }

    public boolean isDamaged() {
        return damage > 0;
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
}
