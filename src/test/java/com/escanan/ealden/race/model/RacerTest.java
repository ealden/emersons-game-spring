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

        assertThat(racer.getPosition(), equalTo(1));
        assertThat(racer.getDamage(), equalTo(0));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(3, NORMAL);

        assertThat(racer.getPosition(), equalTo(1));
        assertThat(racer.getDamage(), equalTo(0));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(5, NORMAL);

        assertThat(racer.getPosition(), equalTo(1));
        assertThat(racer.getDamage(), equalTo(0));
    }

    @Test
    void rollMustSetPositionTo2WithNoDamageIfRollIsEvenAndSpeedTypeNormal() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(2, NORMAL);

        assertThat(racer.getPosition(), equalTo(2));
        assertThat(racer.getDamage(), equalTo(0));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(4, NORMAL);

        assertThat(racer.getPosition(), equalTo(2));
        assertThat(racer.getDamage(), equalTo(0));

        racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(6, NORMAL);

        assertThat(racer.getPosition(), equalTo(2));
        assertThat(racer.getDamage(), equalTo(0));
    }

    @Test
    void rollMustSetPositionToRollWithDamageIfSpeedTypeSuper() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.roll(6, SUPER);

        assertThat(racer.getPosition(), equalTo(6));
        assertThat(racer.getDamage(), equalTo(2));
    }

    @Test
    void rollMustDeductDamageFromRollIfAnyIfSpeedTypeSuper() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setDamage(1);

        racer.roll(6, SUPER);

        assertThat(racer.getPosition(), equalTo(5));
        assertThat(racer.getDamage(), equalTo(3));
    }

    @Test
    void getPositionMustReturnCurrentPositionIfNotYetOnFinishLine() {
        int position = currentRace.getFinishLine() - 1;

        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(position);

        assertThat(racer.getPosition(), equalTo(position));
    }

    @Test
    void getPositionMustReturnFinishLineIfCrossedFinishLine() {
        int position = currentRace.getFinishLine() + 10;

        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(position);

        assertThat(racer.getPosition(), equalTo(currentRace.getFinishLine()));
    }

    @Test
    void isCrashedMustReturnFalseIfDamageLessThanMaxDamage() {
        Racer racer = new Racer();
        racer.setDamage(MAX_DAMAGE - 1);

        assertThat(racer.isCrashed(), equalTo(false));
    }

    @Test
    void isCrashedMustReturnTrueIfDamageEqualToMaxDamage() {
        Racer racer = new Racer();
        racer.setDamage(MAX_DAMAGE);

        assertThat(racer.isCrashed(), equalTo(true));
    }

    @Test
    void isCrashedMustReturnTrueIfDamageMoreThanMaxDamage() {
        Racer racer = new Racer();
        racer.setDamage(MAX_DAMAGE + 1);

        assertThat(racer.isCrashed(), equalTo(true));
    }


    @Test
    void isWinnerMustReturnTrueIfCrossedFinishLine() {
        int position = currentRace.getFinishLine() + 1;

        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(position);

        assertThat(racer.isWinner(), equalTo(true));
    }

    @Test
    void isWinnerMustReturnFalseIfNotYetOnFinishLine() {
        int position = currentRace.getFinishLine() - 1;

        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(position);

        assertThat(racer.isWinner(), equalTo(false));
    }

    @Test
    void rollMustLogRollEntryWithNormalSpeed() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(1);
        racer.setDamage(1);

        assertThat(racer.getLastRoll(), nullValue());

        racer.roll(2, NORMAL);

        Roll roll = racer.getLastRoll();

        assertThat(roll, notNullValue());
        assertThat(roll.getRacer(), sameInstance(racer));
        assertThat(roll.getRace(), sameInstance(currentRace));
        assertThat(roll.getPosition(), equalTo(1));
        assertThat(roll.getDamage(), equalTo(1));
        assertThat(roll.getSpeedType(), equalTo(NORMAL));
        assertThat(roll.getNumber(), equalTo(2));
        assertThat(roll.getMove(), equalTo(1));
        assertThat(roll.getNewPosition(), equalTo(2));
        assertThat(roll.getNewDamage(), equalTo(1));
        assertThat(roll.isCrashed(), equalTo(false));
        assertThat(roll.isWin(), equalTo(false));
    }

    @Test
    void rollMustLogRollEntryWithSuperSpeed() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(1);
        racer.setDamage(1);

        assertThat(racer.getLastRoll(), nullValue());

        racer.roll(2, SUPER);

        Roll roll = racer.getLastRoll();

        assertThat(roll, notNullValue());
        assertThat(roll.getSpeedType(), equalTo(SUPER));
        assertThat(roll.getNumber(), equalTo(2));
        assertThat(roll.getMove(), equalTo(1));
    }

    @Test
    void rollMustLogRollEntryThatCrashed() {
        Racer racer = new Racer();
        racer.setRace(currentRace);

        assertThat(racer.getLastRoll(), nullValue());

        racer.setPosition(1);
        racer.setDamage(5);

        racer.roll(2, SUPER);

        Roll roll = racer.getLastRoll();

        assertThat(roll, notNullValue());
        assertThat(roll.isCrashed(), equalTo(true));
    }

    @Test
    void rollMustLogRollEntryThatWon() {
        Racer racer = new Racer();
        racer.setRace(currentRace);
        racer.setPosition(9);
        racer.setDamage(0);

        assertThat(racer.getLastRoll(), nullValue());

        racer.roll(1, NORMAL);

        Roll roll = racer.getLastRoll();

        assertThat(roll, notNullValue());
        assertThat(roll.isWin(), equalTo(true));
    }

    @Test
    void isDamagedMustBeTrueIfDamageGreaterThanZero() {
        Racer racer = new Racer();
        racer.setDamage(1);

        assertThat(racer.isDamaged(), equalTo(true));
    }

    @Test
    void isDamagedMustBeFalseIfDamageIsZero() {
        Racer racer = new Racer();
        racer.setDamage(0);

        assertThat(racer.isDamaged(), equalTo(false));
    }

    @Test
    void isDamagedMustBeFalseIfDamageIsLessThanZero() {
        Racer racer = new Racer();
        racer.setDamage(-1);

        assertThat(racer.isDamaged(), equalTo(false));
    }

    @Test
    void racersMustBeEqualIfIdsAndNamesAreEqual() {
        Racer racer = new Racer();
        racer.setId(1L);
        racer.setName("Alice");

        Racer otherRacer = new Racer();
        otherRacer.setId(1L);
        otherRacer.setName("Alice");

        assertThat(racer, equalTo(otherRacer));
        assertThat(racer.hashCode(), equalTo(otherRacer.hashCode()));
    }

    @Test
    void racersMustNotBeEqualIfIdsAreNotEqual() {
        Racer racer = new Racer();
        racer.setId(1L);
        racer.setName("Alice");

        Racer otherRacer = new Racer();
        otherRacer.setId(2L);
        otherRacer.setName("Alice");

        assertThat(racer, not(equalTo(otherRacer)));
        assertThat(racer.hashCode(), not(equalTo(otherRacer.hashCode())));
    }

    @Test
    void racersMustNotBeEqualIfNamesAreNotEqual() {
        Racer racer = new Racer();
        racer.setId(1L);
        racer.setName("Alice");

        Racer otherRacer = new Racer();
        otherRacer.setId(1L);
        otherRacer.setName("Bob");

        assertThat(racer, not(equalTo(otherRacer)));
        assertThat(racer.hashCode(), not(equalTo(otherRacer.hashCode())));
    }
}
