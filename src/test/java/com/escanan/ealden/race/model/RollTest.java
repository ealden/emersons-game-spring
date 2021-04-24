package com.escanan.ealden.race.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static com.escanan.ealden.race.model.SpeedType.SUPER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RollTest {
    private Roll roll;

    @BeforeEach
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

    @Test
    public void isNormalSpeedMustReturnTrueIfSpeedTypeIsNormal() {
        roll.setSpeedType(NORMAL);

        assertThat(roll.isNormalSpeed(), is(equalTo(true)));
    }

    @Test
    public void isNormalSpeedMustReturnFalseIfSpeedTypeIsSuper() {
        roll.setSpeedType(SUPER);

        assertThat(roll.isNormalSpeed(), is(equalTo(false)));
    }

    @Test
    public void isSuperSpeedMustReturnTrueIfSpeedTypeIsSuper() {
        roll.setSpeedType(SUPER);

        assertThat(roll.isSuperSpeed(), is(equalTo(true)));
    }

    @Test
    public void isSuperSpeedMustReturnFalseIfSpeedTypeIsNormal() {
        roll.setSpeedType(NORMAL);

        assertThat(roll.isSuperSpeed(), is(equalTo(false)));
    }
}
