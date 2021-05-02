package com.escanan.ealden.race.model;

import org.junit.jupiter.api.Test;

import static com.escanan.ealden.race.model.Racer.MAX_DAMAGE;
import static com.escanan.ealden.race.model.Racer.NO_DAMAGE;
import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static com.escanan.ealden.race.model.SpeedType.SUPER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RaceTest {
    @Test
    void addRacerMustAddRacerToRacers() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        assertThat(race.getRacers(), contains(racer));
    }

    @Test
    void addRacerMustAssociateRacerToRace() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        assertThat(racer.getRace(), sameInstance(race));
    }

    @Test
    void addRacerMustSetFirstRacerAsCurrentRacer() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        Racer racer2 = new Racer();
        race.addRacer(racer2);

        assertThat(race.getCurrentRacer(), sameInstance(racer));
    }

    @Test
    void addRacersMustRankRacers() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        Racer racer2 = new Racer();
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        race.addRacer(racer3);

        assertThat(racer.getRank(), equalTo(1));
        assertThat(racer2.getRank(), equalTo(2));
        assertThat(racer3.getRank(), equalTo(3));
    }

    @Test
    void rollMustNotRollIfNoRacers() {
        Race race = new Race();

        assertDoesNotThrow(() -> {
            race.roll(1, NORMAL);
        });
    }

    @Test
    void rollMustSetCurrentRacerToNextRacerInRank() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        Racer racer2 = new Racer();
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        race.addRacer(racer3);

        race.roll(1, NORMAL);

        assertThat(race.getCurrentRacer(), sameInstance(racer2));
    }

    @Test
    void rollMustSetCurrentRacerToFirstRacerAfterRound() {
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

        assertThat(race.getCurrentRacer(), sameInstance(racer));
    }

    @Test
    void isOverMustReturnFalseIfNoRacersHaveWon() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        Racer racer2 = new Racer();
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        race.addRacer(racer3);

        assertThat(race.isOver(), equalTo(false));
    }

    @Test
    void isOverMustReturnTrueIfARacerHasWon() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);

        Racer racer2 = new Racer();
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        racer3.setPosition(race.getFinishLine());
        race.addRacer(racer3);

        assertThat(race.isOver(), equalTo(true));
    }

    @Test
    void isOverMustReturnTrueIfAllRacersCrashed() {
        Race race = new Race();

        Racer racer = new Racer();
        racer.setDamage(MAX_DAMAGE);
        race.addRacer(racer);

        assertThat(race.isOver(), equalTo(true));
    }

    @Test
    void isAllCrashedMustReturnTrueIfAllRacersCrashed() {
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

        assertThat(race.isAllCrashed(), equalTo(true));
    }

    @Test
    void isAllCrashedMustReturnFalseIfNotAllRacersCrashed() {
        Race race = new Race();

        Racer racer = new Racer();
        race.addRacer(racer);
        racer.setDamage(MAX_DAMAGE);

        Racer racer2 = new Racer();
        racer2.setDamage(MAX_DAMAGE);
        race.addRacer(racer2);

        Racer racer3 = new Racer();
        racer3.setDamage(NO_DAMAGE);
        race.addRacer(racer3);

        assertThat(race.isAllCrashed(), equalTo(false));
    }

    @Test
    void getLastRollMustReturnNullIfNoRollsYet() {
        Race race = new Race();

        assertThat(race.getLastRoll(), nullValue());
    }

    @Test
    void getLastRollMustReturnRollIfRollMade() {
        Race race = new Race();
        race.addRacer(new Racer());

        race.roll(1, NORMAL);

        assertThat(race.getLastRoll(), not(nullValue()));
        assertThat(race.getLastRoll().getRace(), sameInstance(race));
    }

    @Test
    void getMessageWhenNoRacersJoined() {
        Race race = new Race();

        assertThat(race.getMessage(), nullValue());
    }

    @Test
    void getMessageWhenRacersJoined() {
        Race race = new Race();
        race.addRacer(new Racer("Alice"));

        assertThat(race.getMessage(), equalTo("Time to RACE!  Alice rolls first!"));
    }

    @Test
    void getMessageWhenAllRacersCrashed() {
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

        race.roll(1, NORMAL);

        assertThat(race.getMessage(), equalTo("All racers CRASHED!!!  This race is over!"));
    }

    @Test
    void getMessageWhenARacerWins() {
        Race race = new Race();

        Racer racer = new Racer("Alice");
        racer.setPosition(race.getFinishLine());
        race.addRacer(racer);

        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        race.roll(1, NORMAL);

        assertThat(race.getMessage(), equalTo("Alice wins the race!  Congratulations!!!"));
    }

    @Test
    void getMessageWhenLastRacerCrashed() {
        Race race = new Race();

        Racer racer = new Racer("Alice");
        racer.setDamage(MAX_DAMAGE);
        race.addRacer(racer);

        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        race.roll(1, NORMAL);

        assertThat(race.getMessage(), equalTo("Alice chose NORMAL speed, and rolled 1 and moved 0.  Alice CRASHED!!!  Bob rolls next!"));
    }

    @Test
    void getMessageWhenLastRacerDamagedAndRollWithNormalSpeed() {
        Race race = new Race();

        Racer racer = new Racer("Alice");
        racer.setDamage(1);
        race.addRacer(racer);

        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        race.roll(1, NORMAL);

        assertThat(race.getMessage(), equalTo("Alice chose NORMAL speed, and rolled 1 and moved 0.  Alice has 1 damage.  Bob rolls next!"));
    }

    @Test
    void getMessageWhenLastRacerDamagedAndRollWithSuperSpeed() {
        Race race = new Race();

        Racer racer = new Racer("Alice");
        racer.setDamage(1);
        race.addRacer(racer);

        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        race.roll(1, SUPER);

        assertThat(race.getMessage(), equalTo("Alice chose SUPER speed, and rolled 1 and moved 0.  Alice now has 3 damage.  Bob rolls next!"));
    }

    @Test
    void getMessageAfterRacerRolls() {
        Race race = new Race();
        race.addRacer(new Racer("Alice"));
        race.addRacer(new Racer("Bob"));
        race.addRacer(new Racer("Charlie"));
        race.addRacer(new Racer("Dave"));

        race.roll(1, NORMAL);

        assertThat(race.getMessage(), equalTo("Alice chose NORMAL speed, and rolled 1 and moved 1.  Bob rolls next!"));
    }
}
