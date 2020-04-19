package com.escanan.ealden.race.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RollTest {
    private Roll roll;

    @Before
    public void setUp() {
        roll = new Roll();
    }

    @Test
    public void isDamagedMustReturnFalseIfNoNewDamage() {
        assertThat(roll.isDamaged(), is(equalTo(false)));
    }

    @Test
    public void isDamagedMustReturnTrueIfAtLeastOneNewDamage() {
        roll.setNewDamage(1);

        assertThat(roll.isDamaged(), is(equalTo(true)));
    }
}
