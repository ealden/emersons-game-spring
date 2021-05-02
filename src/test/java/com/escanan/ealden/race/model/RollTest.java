package com.escanan.ealden.race.model;

import org.junit.jupiter.api.Test;

import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static com.escanan.ealden.race.model.SpeedType.SUPER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class RollTest {
    @Test
    void isDamagedMustReturnFalseIfNoNewDamage() {
        Roll roll = new Roll();

        assertThat(roll.isDamaged(), equalTo(false));
    }

    @Test
    void isDamagedMustReturnTrueIfAtLeastOneNewDamage() {
        Roll roll = new Roll();
        roll.setNewDamage(1);

        assertThat(roll.isDamaged(), equalTo(true));
    }

    @Test
    void isNormalSpeedMustReturnTrueIfSpeedTypeIsNormal() {
        Roll roll = new Roll();
        roll.setSpeedType(NORMAL);

        assertThat(roll.isNormalSpeed(), equalTo(true));
    }

    @Test
    void isNormalSpeedMustReturnFalseIfSpeedTypeIsSuper() {
        Roll roll = new Roll();
        roll.setSpeedType(SUPER);

        assertThat(roll.isNormalSpeed(), equalTo(false));
    }

    @Test
    void isSuperSpeedMustReturnTrueIfSpeedTypeIsSuper() {
        Roll roll = new Roll();
        roll.setSpeedType(SUPER);

        assertThat(roll.isSuperSpeed(), equalTo(true));
    }

    @Test
    void isSuperSpeedMustReturnFalseIfSpeedTypeIsNormal() {
        Roll roll = new Roll();
        roll.setSpeedType(NORMAL);

        assertThat(roll.isSuperSpeed(), equalTo(false));
    }
}
