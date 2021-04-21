package com.escanan.ealden.race.model;

import org.junit.Before;
import org.junit.Test;

import static com.escanan.ealden.race.model.Racer.MAX_DAMAGE;
import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static com.escanan.ealden.race.model.SpeedType.SUPER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RacerTest {
    private Racer racer;
    private Race race;

    @Before
    public void setUp() {
        racer = new Racer();

        race = new Race();
        racer.setRace(race);
    }

    @Test
    public void rollMustSetPositionTo1WithNoDamageIfRollIsOddAndSpeedTypeNormal() {
        racer.roll(1, NORMAL);

        assertThat(racer.getPosition(), is(1));
        assertThat(racer.getDamage(), is(0));

        racer = new Racer();
        racer.setRace(race);
        racer.roll(3, NORMAL);

        assertThat(racer.getPosition(), is(1));
        assertThat(racer.getDamage(), is(0));

        racer = new Racer();
        racer.setRace(race);
        racer.roll(5, NORMAL);

        assertThat(racer.getPosition(), is(1));
        assertThat(racer.getDamage(), is(0));
    }

    @Test
    public void rollMustSetPositionTo2WithNoDamageIfRollIsEvenAndSpeedTypeNormal() {
        racer.roll(2, NORMAL);

        assertThat(racer.getPosition(), is(2));
        assertThat(racer.getDamage(), is(0));

        racer = new Racer();
        racer.setRace(race);
        racer.roll(4, NORMAL);

        assertThat(racer.getPosition(), is(2));
        assertThat(racer.getDamage(), is(0));

        racer = new Racer();
        racer.setRace(race);
        racer.roll(6, NORMAL);

        assertThat(racer.getPosition(), is(2));
        assertThat(racer.getDamage(), is(0));
    }

    @Test
    public void rollMustSetPositionToRollWithDamageIfSpeedTypeSuper() {
        racer.roll(6, SUPER);

        assertThat(racer.getPosition(), is(6));
        assertThat(racer.getDamage(), is(2));
    }

    @Test
    public void rollMustDeductDamageFromRollIfAnyIfSpeedTypeSuper() {
        racer.setDamage(1);

        racer.roll(6, SUPER);

        assertThat(racer.getPosition(), is(5));
        assertThat(racer.getDamage(), is(3));
    }

    @Test
    public void getPositionMustReturnCurrentPositionIfNotYetOnFinishLine() {
        int position = race.getFinishLine() - 1;

        racer.setPosition(position);

        assertThat(racer.getPosition(), is(position));
    }

    @Test
    public void getPositionMustReturnFinishLineIfCrossedFinishLine() {
        int position = race.getFinishLine() + 10;

        racer.setPosition(position);

        assertThat(racer.getPosition(), is(race.getFinishLine()));
    }

    @Test
    public void isCrashedMustReturnFalseIfDamageLessThanMaxDamage() {
        racer.setDamage(MAX_DAMAGE - 1);

        assertThat(racer.isCrashed(), is(false));
    }

    @Test
    public void isCrashedMustReturnTrueIfDamageEqualToMaxDamage() {
        racer.setDamage(MAX_DAMAGE);

        assertThat(racer.isCrashed(), is(true));
    }

    @Test
    public void isCrashedMustReturnTrueIfDamageMoreThanMaxDamage() {
        racer.setDamage(MAX_DAMAGE + 1);

        assertThat(racer.isCrashed(), is(true));
    }


    @Test
    public void isWinnerMustReturnTrueIfCrossedFinishLine() {
        int position = race.getFinishLine() + 1;

        racer.setPosition(position);

        assertThat(racer.isWinner(), is(true));
    }

    @Test
    public void isWinnerMustReturnFalseIfNotYetOnFinishLine() {
        int position = race.getFinishLine() - 1;

        racer.setPosition(position);

        assertThat(racer.isWinner(), is(false));
    }

    @Test
    public void rollMustLogRollEntryWithNormalSpeed() {
        assertThat(racer.getLastRoll(), is(nullValue()));

        racer.setPosition(1);
        racer.setDamage(1);

        racer.roll(2, NORMAL);

        Roll roll = racer.getLastRoll();

        assertThat(roll, is(not(nullValue())));
        assertThat(roll.getRacer(), is(sameInstance(racer)));
        assertThat(roll.getRace(), is(sameInstance(race)));
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
    public void rollMustLogRollEntryWithSuperSpeed() {
        assertThat(racer.getLastRoll(), is(nullValue()));

        racer.setPosition(1);
        racer.setDamage(1);

        racer.roll(2, SUPER);

        Roll roll = racer.getLastRoll();

        assertThat(roll, is(not(nullValue())));
        assertThat(roll.getSpeedType(), is(equalTo(SUPER)));
        assertThat(roll.getNumber(), is(equalTo(2)));
        assertThat(roll.getMove(), is(equalTo(1)));
    }

    @Test
    public void rollMustLogRollEntryThatCrashed() {
        assertThat(racer.getLastRoll(), is(nullValue()));

        racer.setPosition(1);
        racer.setDamage(5);

        racer.roll(2, SUPER);

        Roll roll = racer.getLastRoll();

        assertThat(roll, is(not(nullValue())));
        assertThat(roll.isCrashed(), is(true));
    }

    @Test
    public void rollMustLogRollEntryThatWon() {
        assertThat(racer.getLastRoll(), is(nullValue()));

        racer.setPosition(9);
        racer.setDamage(0);

        racer.roll(1, NORMAL);

        Roll roll = racer.getLastRoll();

        assertThat(roll, is(not(nullValue())));
        assertThat(roll.isWin(), is(true));
    }

    @Test
    public void isDamagedMustBeTrueIfDamageGreaterThanZero() {
        racer.setDamage(1);

        assertThat(racer.isDamaged(), is(true));
    }

    @Test
    public void isDamagedMustBeFalseIfDamageIsZero() {
        racer.setDamage(0);

        assertThat(racer.isDamaged(), is(false));
    }

    @Test
    public void isDamagedMustBeFalseIfDamageIsLessThanZero() {
        racer.setDamage(-1);

        assertThat(racer.isDamaged(), is(false));
    }

    @Test
    public void racersMustBeEqualIfIdsAndNamesAreEqual() {
        racer.setId(1L);
        racer.setName("Alice");

        Racer otherRacer = new Racer();
        otherRacer.setId(1L);
        otherRacer.setName("Alice");

        assertThat(racer, is(equalTo(otherRacer)));
        assertThat(racer.hashCode(), is(equalTo(otherRacer.hashCode())));
    }

    @Test
    public void racersMustNotBeEqualIfIdsAreNotEqual() {
        racer.setId(1L);
        racer.setName("Alice");

        Racer otherRacer = new Racer();
        otherRacer.setId(2L);
        otherRacer.setName("Alice");

        assertThat(racer, is(not(equalTo(otherRacer))));
        assertThat(racer.hashCode(), is(not(equalTo(otherRacer.hashCode()))));
    }

    @Test
    public void racersMustNotBeEqualIfNamesAreNotEqual() {
        racer.setId(1L);
        racer.setName("Alice");

        Racer otherRacer = new Racer();
        otherRacer.setId(1L);
        otherRacer.setName("Bob");

        assertThat(racer, is(not(equalTo(otherRacer))));
        assertThat(racer.hashCode(), is(not(equalTo(otherRacer.hashCode()))));
    }
}
