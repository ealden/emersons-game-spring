package com.escanan.ealden.race.model;

import org.junit.Before;
import org.junit.Test;

import static com.escanan.ealden.race.model.Racer.SpeedType.NORMAL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RaceTest {
    private Race race;

    @Before
    public void setUp() {
        race = new Race();
    }

    @Test
    public void addRacerMustAddRacerToRacers() {
        Racer racer = new Racer();

        race.addRacer(racer);

        assertThat(race.getRacers(), contains(racer));
    }

    @Test
    public void addRacerMustAssociateRacerToRace() {
        Racer racer = new Racer();

        race.addRacer(racer);

        assertThat(racer.getRace(), is(sameInstance(race)));
    }

    @Test
    public void addRacerMustSetFirstRacerAsCurrentRacer() {
        Racer racer = new Racer();
        Racer racer2 = new Racer();

        race.addRacer(racer);
        race.addRacer(racer2);

        assertThat(race.getCurrentRacer(), is(sameInstance(racer)));
    }

    @Test
    public void addRacersMustRankRacers() {
        Racer racer = new Racer();
        Racer racer2 = new Racer();
        Racer racer3 = new Racer();

        race.addRacer(racer);
        race.addRacer(racer2);
        race.addRacer(racer3);

        assertThat(racer.getRank(), is(1));
        assertThat(racer2.getRank(), is(2));
        assertThat(racer3.getRank(), is(3));
    }

    @Test
    public void rollMustNotRollIfNoRacers() {
        assertDoesNotThrow(() -> {
            race.roll(1, NORMAL);
        });
    }

    @Test
    public void rollMustSetCurrentRacerToNextRacerInRank() {
        Racer racer = new Racer();
        Racer racer2 = new Racer();
        Racer racer3 = new Racer();

        race.addRacer(racer);
        race.addRacer(racer2);
        race.addRacer(racer3);

        race.roll(1, NORMAL);

        assertThat(race.getCurrentRacer(), is(sameInstance(racer2)));
    }

    @Test
    public void rollMustSetCurrentRacerToFirstRacerAfterRound() {
        Racer racer = new Racer();
        Racer racer2 = new Racer();
        Racer racer3 = new Racer();

        race.addRacer(racer);
        race.addRacer(racer2);
        race.addRacer(racer3);

        race.roll(1, NORMAL);
        race.roll(1, NORMAL);
        race.roll(1, NORMAL);

        assertThat(race.getCurrentRacer(), is(sameInstance(racer)));
    }

    @Test
    public void isOverMustBeFalseIfNoRacersHaveWon() {
        Racer racer = new Racer();
        Racer racer2 = new Racer();
        Racer racer3 = new Racer();

        race.addRacer(racer);
        race.addRacer(racer2);
        race.addRacer(racer3);

        assertThat(race.isOver(), is(false));
    }

    @Test
    public void isOverMustBeTrueIfARacerHasWon() {
        Racer racer = new Racer();
        Racer racer2 = new Racer();
        Racer racer3 = new Racer();

        racer3.setPosition(race.getFinishLine());

        race.addRacer(racer);
        race.addRacer(racer2);
        race.addRacer(racer3);

        assertThat(race.isOver(), is(true));
    }
}
