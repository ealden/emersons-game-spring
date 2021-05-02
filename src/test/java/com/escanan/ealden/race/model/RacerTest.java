package com.escanan.ealden.race.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.escanan.ealden.race.model.Racer.MAX_DAMAGE;
import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static com.escanan.ealden.race.model.SpeedType.SUPER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class RacerTest {
    private Race currentRace;

    @BeforeEach
    void setUp() {
        currentRace = new Race();
    }

    @Test
    void rollMustSetPositionTo1WithNoDamageIfRollIsOddAndSpeedTypeNormal() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(1, NORMAL);

        assertThat(racer.getPosition(), is(1));
        assertThat(racer.getDamage(), is(0));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(3, NORMAL);

        assertThat(racer.getPosition(), is(1));
        assertThat(racer.getDamage(), is(0));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(5, NORMAL);

        assertThat(racer.getPosition(), is(1));
        assertThat(racer.getDamage(), is(0));
    }

    @Test
    void rollMustSetPositionTo2WithNoDamageIfRollIsEvenAndSpeedTypeNormal() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(2, NORMAL);

        assertThat(racer.getPosition(), is(2));
        assertThat(racer.getDamage(), is(0));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(4, NORMAL);

        assertThat(racer.getPosition(), is(2));
        assertThat(racer.getDamage(), is(0));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(6, NORMAL);

        assertThat(racer.getPosition(), is(2));
        assertThat(racer.getDamage(), is(0));
    }

    @Test
    void rollMustSetPositionToRollWithDamageIfSpeedTypeSuper() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(6, SUPER);

        assertThat(racer.getPosition(), is(6));
        assertThat(racer.getDamage(), is(2));
    }

    @Test
    void rollMustDeductDamageFromRollIfAnyIfSpeedTypeSuper() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setDamage(1);

        racer.roll(6, SUPER);

        assertThat(racer.getPosition(), is(5));
        assertThat(racer.getDamage(), is(3));
    }

    @Test
    void getPositionMustReturnCurrentPositionIfNotYetOnFinishLine() {
        int position = currentRace.getFinishLine() - 1;

        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(position);

        assertThat(racer.getPosition(), is(position));
    }

    @Test
    void getPositionMustReturnFinishLineIfCrossedFinishLine() {
        int position = currentRace.getFinishLine() + 10;

        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(position);

        assertThat(racer.getPosition(), is(currentRace.getFinishLine()));
    }

    @Test
    void isCrashedMustReturnFalseIfDamageLessThanMaxDamage() {
        Racer racer = new Racer();
        racer.setDamage(MAX_DAMAGE - 1);

        assertThat(racer.isCrashed(), is(false));
    }

    @Test
    void isCrashedMustReturnTrueIfDamageEqualToMaxDamage() {
        Racer racer = new Racer();
        racer.setDamage(MAX_DAMAGE);

        assertThat(racer.isCrashed(), is(true));
    }

    @Test
    void isCrashedMustReturnTrueIfDamageMoreThanMaxDamage() {
        Racer racer = new Racer();
        racer.setDamage(MAX_DAMAGE + 1);

        assertThat(racer.isCrashed(), is(true));
    }


    @Test
    void isWinnerMustReturnTrueIfCrossedFinishLine() {
        int position = currentRace.getFinishLine() + 1;

        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(position);

        assertThat(racer.isWinner(), is(true));
    }

    @Test
    void isWinnerMustReturnFalseIfNotYetOnFinishLine() {
        int position = currentRace.getFinishLine() - 1;

        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(position);

        assertThat(racer.isWinner(), is(false));
    }

    @Test
    void rollMustLogRollEntryWithNormalSpeed() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(1);
        racer.setDamage(1);

        assertThat(racer.getLastRoll(), is(nullValue()));

        racer.roll(2, NORMAL);

        Roll roll = racer.getLastRoll();

        assertThat(roll, is(not(nullValue())));
        assertThat(roll.getRacer(), is(sameInstance(racer)));
        assertThat(roll.getRace(), is(sameInstance(currentRace)));
        assertThat(roll.getPosition(), is(equalTo(1)));
        assertThat(roll.getDamage(), is(equalTo(1)));
        assertThat(roll.getSpeedType(), is(equalTo(NORMAL)));
        assertThat(roll.getNumber(), is(equalTo(2)));
        assertThat(roll.getMove(), is(equalTo(1)));
        assertThat(roll.getNewPosition(), is(equalTo(2)));
        assertThat(roll.getNewDamage(), is(equalTo(1)));
        assertThat(roll.isCrashed(), is(false));
        assertThat(roll.isWin(), is(false));
    }

    @Test
    void rollMustLogRollEntryWithSuperSpeed() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(1);
        racer.setDamage(1);

        assertThat(racer.getLastRoll(), is(nullValue()));

        racer.roll(2, SUPER);

        Roll roll = racer.getLastRoll();

        assertThat(roll, is(not(nullValue())));
        assertThat(roll.getSpeedType(), is(equalTo(SUPER)));
        assertThat(roll.getNumber(), is(equalTo(2)));
        assertThat(roll.getMove(), is(equalTo(1)));
    }

    @Test
    void rollMustLogRollEntryThatCrashed() {
        Racer racer = new Racer();
        racer.setRace(currentRace);

        assertThat(racer.getLastRoll(), is(nullValue()));

        racer.setPosition(1);
        racer.setDamage(5);

        racer.roll(2, SUPER);

        Roll roll = racer.getLastRoll();

        assertThat(roll, is(not(nullValue())));
        assertThat(roll.isCrashed(), is(true));
    }

    @Test
    void rollMustLogRollEntryThatWon() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(9);
        racer.setDamage(0);

        assertThat(racer.getLastRoll(), is(nullValue()));

        racer.roll(1, NORMAL);

        Roll roll = racer.getLastRoll();

        assertThat(roll, is(not(nullValue())));
        assertThat(roll.isWin(), is(true));
    }

    @Test
    void isDamagedMustBeTrueIfDamageGreaterThanZero() {
        Racer racer = new Racer();
        racer.setDamage(1);

        assertThat(racer.isDamaged(), is(true));
    }

    @Test
    void isDamagedMustBeFalseIfDamageIsZero() {
        Racer racer = new Racer();
        racer.setDamage(0);

        assertThat(racer.isDamaged(), is(false));
    }

    @Test
    void isDamagedMustBeFalseIfDamageIsLessThanZero() {
        Racer racer = new Racer();
        racer.setDamage(-1);

        assertThat(racer.isDamaged(), is(false));
    }

    @Test
    void racersMustBeEqualIfIdsAndNamesAreEqual() {
        Racer racer = new Racer();
        racer.setId(1L);
        racer.setName("Alice");

        Racer otherRacer = new Racer();
        otherRacer.setId(1L);
        otherRacer.setName("Alice");

        assertThat(racer, is(equalTo(otherRacer)));
        assertThat(racer.hashCode(), is(equalTo(otherRacer.hashCode())));
    }

    @Test
    void racersMustNotBeEqualIfIdsAreNotEqual() {
        Racer racer = new Racer();
        racer.setId(1L);
        racer.setName("Alice");

        Racer otherRacer = new Racer();
        otherRacer.setId(2L);
        otherRacer.setName("Alice");

        assertThat(racer, is(not(equalTo(otherRacer))));
        assertThat(racer.hashCode(), is(not(equalTo(otherRacer.hashCode()))));
    }

    @Test
    void racersMustNotBeEqualIfNamesAreNotEqual() {
        Racer racer = new Racer();
        racer.setId(1L);
        racer.setName("Alice");

        Racer otherRacer = new Racer();
        otherRacer.setId(1L);
        otherRacer.setName("Bob");

        assertThat(racer, is(not(equalTo(otherRacer))));
        assertThat(racer.hashCode(), is(not(equalTo(otherRacer.hashCode()))));
    }
}
