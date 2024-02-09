package com.escanan.ealden.race.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.escanan.ealden.race.model.Roll.createRoll;
import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

@Entity
public class Racer {
    public static final int MAX_DAMAGE = 6;
    public static final int NO_DAMAGE = 0;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Race race;

    @OneToMany(mappedBy = "racer", cascade = ALL, fetch = EAGER)
    @OrderBy("id")
    private final List<Roll> rolls = new ArrayList<>();

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

    public void roll(int number, SpeedType speedType) {
        int oldPosition = position;
        int oldDamage = damage;

        position += speedType.move(number, damage);
        damage += speedType.getDamage();

        addRoll(createRoll(this, oldPosition, oldDamage, number, speedType));
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
        return damage > NO_DAMAGE;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Racer racer = (Racer) o;

        return Objects.equals(id, racer.id) && Objects.equals(name, racer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
