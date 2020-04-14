package gradle.cucumber;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.web.server.LocalServerPort;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class RaceSteps {
    @LocalServerPort
    private int port;

    private WebDriver driver;

    @When("I visit the race track")
    public void visitRace() {
        driver().navigate().to(String.format("http://localhost:%s/", port));
    }

    @Then("I must see the race")
    public void assertSeeRace() {
        var h1 = driver().findElement(By.tagName("h1"));

        assertThat(h1.getText(), is(equalTo("Emerson's Game")));
    }

    private WebDriver driver() {
        if (driver == null) {
            driver = new ChromeDriver(new ChromeOptions().addArguments("--headless"));
        }

        return driver;
    }
}
