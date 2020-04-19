package com.escanan.ealden.race.model;

import org.junit.Test;

import static com.escanan.ealden.race.model.SpeedType.NORMAL;
import static com.escanan.ealden.race.model.SpeedType.SUPER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SpeedTypeTest {
    @Test
    public void calculate() {
        assertThat(NORMAL.calculate(1), is(equalTo(1)));
        assertThat(NORMAL.calculate(2), is(equalTo(2)));
        assertThat(NORMAL.calculate(3), is(equalTo(1)));
        assertThat(NORMAL.calculate(4), is(equalTo(2)));
        assertThat(NORMAL.calculate(5), is(equalTo(1)));
        assertThat(NORMAL.calculate(6), is(equalTo(2)));

        assertThat(SUPER.calculate(1), is(equalTo(1)));
        assertThat(SUPER.calculate(2), is(equalTo(2)));
        assertThat(SUPER.calculate(3), is(equalTo(3)));
        assertThat(SUPER.calculate(4), is(equalTo(4)));
        assertThat(SUPER.calculate(5), is(equalTo(5)));
        assertThat(SUPER.calculate(6), is(equalTo(6)));
    }

    @Test
    public void move() {
        assertThat(NORMAL.move(5, 0), is(equalTo(1)));
        assertThat(NORMAL.move(5, 1), is(equalTo(0)));
        assertThat(NORMAL.move(5, 2), is(equalTo(0)));
        assertThat(SUPER.move(5, 0), is(equalTo(5)));
        assertThat(SUPER.move(5, 1), is(equalTo(4)));
        assertThat(SUPER.move(5, 6), is(equalTo(0)));
    }
}
