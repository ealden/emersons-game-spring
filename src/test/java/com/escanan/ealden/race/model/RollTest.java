package com.escanan.ealden.race.model;

import org.junit.jupiter.api.Test;

import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static com.escanan.ealden.race.model.SpeedType.SUPER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class RollTest {
    @Test
    void isDamagedMustReturnFalseIfNoNewDamage() {
        Roll roll = new Roll();

        assertThat(roll.isDamaged(), is(equalTo(false)));
    }

    @Test
    void isDamagedMustReturnTrueIfAtLeastOneNewDamage() {
        Roll roll = new Roll();
        roll.setNewDamage(1);

        assertThat(roll.isDamaged(), is(equalTo(true)));
    }

    @Test
    void isNormalSpeedMustReturnTrueIfSpeedTypeIsNormal() {
        Roll roll = new Roll();
        roll.setSpeedType(NORMAL);

        assertThat(roll.isNormalSpeed(), is(equalTo(true)));
    }

    @Test
    void isNormalSpeedMustReturnFalseIfSpeedTypeIsSuper() {
        Roll roll = new Roll();
        roll.setSpeedType(SUPER);

        assertThat(roll.isNormalSpeed(), is(equalTo(false)));
    }

    @Test
    void isSuperSpeedMustReturnTrueIfSpeedTypeIsSuper() {
        Roll roll = new Roll();
        roll.setSpeedType(SUPER);

        assertThat(roll.isSuperSpeed(), is(equalTo(true)));
    }

    @Test
    void isSuperSpeedMustReturnFalseIfSpeedTypeIsNormal() {
        Roll roll = new Roll();
        roll.setSpeedType(NORMAL);

        assertThat(roll.isSuperSpeed(), is(equalTo(false)));
    }
}
