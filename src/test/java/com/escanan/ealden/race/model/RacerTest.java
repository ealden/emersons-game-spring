package com.escanan.ealden.race.model;

import org.junit.Before;
import org.junit.Test;

import static com.escanan.ealden.race.model.Racer.SpeedType.NORMAL;
import static com.escanan.ealden.race.model.Racer.SpeedType.SUPER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RacerTest {
    private Racer racer;

    @Before
    public void setUp() {
        racer = new Racer();
    }

    @Test
    public void rollMustSetPositionTo1WithNoDamageIfRollIsOddAndSpeedTypeNormal() {
        racer.roll(1, NORMAL);

        assertThat(racer.getPosition(), is(1));
        assertThat(racer.getDamage(), is(0));

        racer = new Racer();
        racer.roll(3, NORMAL);

        assertThat(racer.getPosition(), is(1));
        assertThat(racer.getDamage(), is(0));

        racer = new Racer();
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
        racer.roll(4, NORMAL);

        assertThat(racer.getPosition(), is(2));
        assertThat(racer.getDamage(), is(0));

        racer = new Racer();
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
}
