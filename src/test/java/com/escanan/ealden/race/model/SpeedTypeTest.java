package com.escanan.ealden.race.model;

import org.junit.jupiter.api.Test;

import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static com.escanan.ealden.race.model.SpeedType.SUPER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class SpeedTypeTest {
    @Test
    void calculate() {
        assertThat(NORMAL.calculate(1), equalTo(1));
        assertThat(NORMAL.calculate(2), equalTo(2));
        assertThat(NORMAL.calculate(3), equalTo(1));
        assertThat(NORMAL.calculate(4), equalTo(2));
        assertThat(NORMAL.calculate(5), equalTo(1));
        assertThat(NORMAL.calculate(6), equalTo(2));

        assertThat(NORMAL.calculate(0), equalTo(0));
        assertThat(NORMAL.calculate(-1), equalTo(0));

        assertThat(SUPER.calculate(1), equalTo(1));
        assertThat(SUPER.calculate(2), equalTo(2));
        assertThat(SUPER.calculate(3), equalTo(3));
        assertThat(SUPER.calculate(4), equalTo(4));
        assertThat(SUPER.calculate(5), equalTo(5));
        assertThat(SUPER.calculate(6), equalTo(6));

        assertThat(SUPER.calculate(0), equalTo(0));
        assertThat(SUPER.calculate(-1), equalTo(0));
    }

    @Test
    void move() {
        assertThat(NORMAL.move(5, 0), equalTo(1));
        assertThat(NORMAL.move(5, 1), equalTo(0));
        assertThat(NORMAL.move(5, 2), equalTo(0));
        assertThat(SUPER.move(5, 0), equalTo(5));
        assertThat(SUPER.move(5, 1), equalTo(4));
        assertThat(SUPER.move(5, 6), equalTo(0));
    }
}
