package com.escanan.ealden.race.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
}
