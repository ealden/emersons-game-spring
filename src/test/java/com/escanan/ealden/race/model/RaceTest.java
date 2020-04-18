package com.escanan.ealden.race.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;

public class RaceTest {
    private Race race;

    @Before
    public void setUp() {
        race = new Race();
    }

    @Test
    public void addRacerMustAssociateRacerToRace() {
        Racer racer = new Racer();

        race.addRacer(racer);

        assertThat(racer.getRace(), is(sameInstance(race)));
    }
}
