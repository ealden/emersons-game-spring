package com.escanan.ealden.race.model;

import org.junit.Before;
import org.junit.Test;

import static com.escanan.ealden.race.model.Racer.MAX_DAMAGE;
import static com.escanan.ealden.race.model.Racer.SpeedType.NORMAL;
import static com.escanan.ealden.race.model.Racer.SpeedType.SUPER;
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
        assertThat(racer.getDamage(), is(1));
    }

    @Test
    public void rollMustDeductDamageFromRollIfAnyIfSpeedTypeSuper() {
        racer.setDamage(1);

        racer.roll(6, SUPER);

        assertThat(racer.getPosition(), is(5));
        assertThat(racer.getDamage(), is(2));
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
    public void isCrashedMustBeFalseIfDamageLessThanMaxDamage() {
        racer.setDamage(MAX_DAMAGE - 1);

        assertThat(racer.isCrashed(), is(false));
    }

    @Test
    public void isCrashedMustBeTrueIfDamageEqualToMaxDamage() {
        racer.setDamage(MAX_DAMAGE);

        assertThat(racer.isCrashed(), is(true));
    }

    @Test
    public void isCrashedMustBeTrueIfDamageMoreThanMaxDamage() {
        racer.setDamage(MAX_DAMAGE + 1);

        assertThat(racer.isCrashed(), is(true));
    }


    @Test
    public void isWinnerMustBeTrueIfCrossedFinishLine() {
        int position = race.getFinishLine() + 1;

        racer.setPosition(position);

        assertThat(racer.isWinner(), is(true));
    }

    @Test
    public void isWinnerMustBeFalseIfNotYetOnFinishLine() {
        int position = race.getFinishLine() - 1;

        racer.setPosition(position);

        assertThat(racer.isWinner(), is(false));
    }

    @Test
    public void rollMustLogRollEntry() {
        assertThat(racer.getLastRoll(), is(nullValue()));

        racer.setPosition(1);

        racer.roll(1, NORMAL);

        Roll roll = racer.getLastRoll();

        assertThat(roll, is(not(nullValue())));
        assertThat(roll.getRacer(), is(sameInstance(racer)));
        assertThat(roll.getRace(), is(sameInstance(race)));
        assertThat(roll.getPosition(), is(equalTo(1)));
    }
}
