package com.escanan.ealden.race.steps;

import com.escanan.ealden.race.page.RacePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.web.server.LocalServerPort;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RaceSteps {
    @LocalServerPort
    private int port;

    private RacePage page;

    @When("I visit the race track")
    public void visitRace() {
        page = RacePage.open();
    }

    @Then("I must see the race")
    public void assertSeeRace() {
        assertThat(page.getHeader().getText(), is(equalTo("Emerson's Game")));
    }
}
