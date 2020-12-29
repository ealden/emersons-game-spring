package com.escanan.ealden.race;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@CucumberContextConfiguration
@SpringBootTest(
        classes = RaceApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {
                "logging.pattern.console=",
                "race.test.enable=true",
                "race.test.headless=${EMERSONS_GAME_HEADLESS:true}"
        }
)
class RaceApplicationTest {
    @Test
    void contextLoads() {
        assertThat(true, is(equalTo(true)));
    }
}
