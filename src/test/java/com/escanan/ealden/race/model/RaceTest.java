package com.escanan.ealden.race.model;

import org.junit.jupiter.api.Test;

import static com.escanan.ealden.race.model.Racer.MAX_DAMAGE;
import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static com.escanan.ealden.race.model.SpeedType.SUPER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RaceTest {
    @Test
    public void addRacerMustAddRacerToRacers() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        assertThat(race.getRacers(), contains(racer));
    }

    @Test
    public void addRacerMustAssociateRacerToRace() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        assertThat(racer.getRace(), is(sameInstance(race)));
    }

    @Test
    public void addRacerMustSetFirstRacerAsCurrentRacer() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        Racer racer2 = new Racer();
        race.addRacer(racer2);

        assertThat(race.getCurrentRacer(), is(sameInstance(racer)));
    }

    @Test
    public void addRacersMustRankRacers() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        Racer racer2 = new Racer();
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        race.addRacer(racer3);

        assertThat(racer.getRank(), is(1));
        assertThat(racer2.getRank(), is(2));
        assertThat(racer3.getRank(), is(3));
    }

    @Test
    public void rollMustNotRollIfNoRacers() {
        Race race = new Race();

        assertDoesNotThrow(() -> {
            race.roll(1, NORMAL);
        });
    }

    @Test
    public void rollMustSetCurrentRacerToNextRacerInRank() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        Racer racer2 = new Racer();
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        race.addRacer(racer3);

        race.roll(1, NORMAL);

        assertThat(race.getCurrentRacer(), is(sameInstance(racer2)));
    }

    @Test
    public void rollMustSetCurrentRacerToFirstRacerAfterRound() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        Racer racer2 = new Racer();
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        race.addRacer(racer3);

        race.roll(1, NORMAL);
        race.roll(1, NORMAL);
        race.roll(1, NORMAL);

        assertThat(race.getCurrentRacer(), is(sameInstance(racer)));
    }

    @Test
    public void isOverMustReturnFalseIfNoRacersHaveWon() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        Racer racer2 = new Racer();
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        race.addRacer(racer3);

        assertThat(race.isOver(), is(false));
    }

    @Test
    public void isOverMustReturnTrueIfARacerHasWon() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        Racer racer2 = new Racer();
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        racer3.setPosition(race.getFinishLine());
        race.addRacer(racer3);

        assertThat(race.isOver(), is(true));
    }

    @Test
    public void isOverMustReturnTrueIfAllRacersCrashed() {
        Race race = new Race();

        Racer racer = new Racer();
        racer.setDamage(MAX_DAMAGE);
        race.addRacer(racer);

        assertThat(race.isOver(), is(true));
    }

    @Test
    public void isAllCrashedMustReturnTrueIfAllRacersCrashed() {
        Race race = new Race();

        Racer racer = new Racer();
        racer.setDamage(MAX_DAMAGE);
        race.addRacer(racer);

        Racer racer2 = new Racer();
        racer2.setDamage(MAX_DAMAGE);
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        racer3.setDamage(MAX_DAMAGE);
        race.addRacer(racer3);

        assertThat(race.isAllCrashed(), is(true));
    }

    @Test
    public void isAllCrashedMustReturnFalseIfNotAllRacersCrashed() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);
        racer.setDamage(MAX_DAMAGE);

        Racer racer2 = new Racer();
        racer2.setDamage(MAX_DAMAGE);
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        race.addRacer(racer3);

        assertThat(race.isAllCrashed(), is(false));
    }

    @Test
    public void getLastRollMustReturnNullIfNoRollsYet() {
        Race race = new Race();

        assertThat(race.getLastRoll(), is(nullValue()));
    }

    @Test
    public void getLastRollMustReturnRollIfRollMade() {
        Race race = new Race();
        race.addRacer(new Racer());

        race.roll(1, NORMAL);

        assertThat(race.getLastRoll(), is(not(nullValue())));
        assertThat(race.getLastRoll().getRace(), is(sameInstance(race)));
    }

    @Test
    public void getMessageWhenNoRacersJoined() {
        Race race = new Race();

        assertThat(race.getMessage(), is(nullValue()));
    }

    @Test
    public void getMessageWhenRacersJoined() {
        Race race = new Race();
        race.addRacer(new Racer("Alice"));

        assertThat(race.getMessage(), is(equalTo("Time to RACE!  Alice rolls first!")));
    }

    @Test
    public void getMessageWhenAllRacersCrashed() {
        Race race = new Race();

        Racer racer = new Racer();
        racer.setDamage(MAX_DAMAGE);
        race.addRacer(racer);

        Racer racer2 = new Racer();
        racer2.setDamage(MAX_DAMAGE);
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        racer3.setDamage(MAX_DAMAGE);
        race.addRacer(racer3);

        race.roll(0, NORMAL);

        assertThat(race.getMessage(), is(equalTo("All racers CRASHED!!!  This race is over!")));
    }

    @Test
    public void getMessageWhenARacerWins() {
        Race race = new Race();

        Racer racer = new Racer("Alice");
        racer.setPosition(race.getFinishLine());
        race.addRacer(racer);

        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        race.roll(0, NORMAL);

        assertThat(race.getMessage(), is(equalTo("Alice wins the race!  Congratulations!!!")));
    }

    @Test
    public void getMessageWhenLastRacerCrashed() {
        Race race = new Race();

        Racer racer = new Racer("Alice");
        racer.setDamage(MAX_DAMAGE);
        race.addRacer(racer);

        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        race.roll(0, NORMAL);

        assertThat(race.getMessage(), is(equalTo("Alice chose NORMAL speed, and rolled 0 and moved 0.  Alice CRASHED!!!  Bob rolls next!")));
    }

    @Test
    public void getMessageWhenLastRacerDamagedAndRollWithNormalSpeed() {
        Race race = new Race();

        Racer racer = new Racer("Alice");
        racer.setDamage(1);
        race.addRacer(racer);

        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        race.roll(0, NORMAL);

        assertThat(race.getMessage(), is(equalTo("Alice chose NORMAL speed, and rolled 0 and moved 0.  Alice has 1 damage.  Bob rolls next!")));
    }

    @Test
    public void getMessageWhenLastRacerDamagedAndRollWithSuperSpeed() {
        Race race = new Race();

        Racer racer = new Racer("Alice");
        racer.setDamage(1);
        race.addRacer(racer);

        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        race.roll(0, SUPER);

        assertThat(race.getMessage(), is(equalTo("Alice chose SUPER speed, and rolled 0 and moved 0.  Alice now has 3 damage.  Bob rolls next!")));
    }

    @Test
    public void getMessageAfterRacerRolls() {
        Race race = new Race();
        race.addRacer(new Racer("Alice"));
        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        race.roll(0, NORMAL);

        assertThat(race.getMessage(), is(equalTo("Alice chose NORMAL speed, and rolled 0 and moved 0.  Bob rolls next!")));
    }
}
