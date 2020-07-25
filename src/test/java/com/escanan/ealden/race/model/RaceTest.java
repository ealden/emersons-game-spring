package com.escanan.ealden.race.model;

import org.junit.Before;
import org.junit.Test;

import static com.escanan.ealden.race.model.Racer.MAX_DAMAGE;
import static com.escanan.ealden.race.model.SpeedType.NORMAL;
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
    public void isOverMustReturnFalseIfNoRacersHaveWon() {
        Racer racer = new Racer();
        Racer racer2 = new Racer();
        Racer racer3 = new Racer();

        race.addRacer(racer);
        race.addRacer(racer2);
        race.addRacer(racer3);

        assertThat(race.isOver(), is(false));
    }

    @Test
    public void isOverMustReturnTrueIfARacerHasWon() {
        Racer racer = new Racer();
        Racer racer2 = new Racer();
        Racer racer3 = new Racer();

        race.addRacer(racer);
        race.addRacer(racer2);
        race.addRacer(racer3);

        racer3.setPosition(race.getFinishLine());

        assertThat(race.isOver(), is(true));
    }

    @Test
    public void isAllCrashedMustReturnTrueIfAllRacersCrashed() {
        Racer racer = new Racer();
        Racer racer2 = new Racer();
        Racer racer3 = new Racer();

        race.addRacer(racer);
        race.addRacer(racer2);
        race.addRacer(racer3);

        racer.setDamage(MAX_DAMAGE);
        racer2.setDamage(MAX_DAMAGE);
        racer3.setDamage(MAX_DAMAGE);

        assertThat(race.isAllCrashed(), is(true));
    }

    @Test
    public void isAllCrashedMustReturnFalseIfNotAllRacersCrashed() {
        Racer racer = new Racer();
        Racer racer2 = new Racer();
        Racer racer3 = new Racer();

        race.addRacer(racer);
        race.addRacer(racer2);
        race.addRacer(racer3);

        racer.setDamage(MAX_DAMAGE);
        racer2.setDamage(MAX_DAMAGE);

        assertThat(race.isAllCrashed(), is(false));
    }

    @Test
    public void isOverMustReturnTrueIfAllRacersCrashed() {
        Racer racer = new Racer();

        race.addRacer(racer);

        racer.setDamage(MAX_DAMAGE);

        assertThat(race.isOver(), is(true));
    }

    @Test
    public void getLastRollMustReturnNullIfNoRollsYet() {
        assertThat(race.getLastRoll(), is(nullValue()));
    }

    @Test
    public void getLastRollMustReturnRollIfRollMade() {
        Racer racer = new Racer();

        race.addRacer(racer);

        race.roll(1, NORMAL);

        assertThat(race.getLastRoll(), is(not(nullValue())));
        assertThat(race.getLastRoll().getRace(), is(sameInstance(race)));
    }

    @Test
    public void getMessageWithNoRacers() {
        assertThat(race.getMessage(), is(nullValue()));
    }

    @Test
    public void getMessageWithRacers() {
        race.addRacer(new Racer("Racer 1"));

        assertThat(race.getMessage(), is(equalTo("Time to RACE!  Racer 1 rolls first!")));
    }
}
