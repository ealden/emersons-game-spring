package gradle.cucumber;

import com.escanan.ealden.race.RaceApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = RaceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestApplication {
}
