package com.escanan.ealden.race;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(
        classes = RaceApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {"logging.pattern.console="}
)
class RaceApplicationTest {
    @Test
    void contextLoads() {
    }
}
