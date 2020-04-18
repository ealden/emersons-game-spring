package com.escanan.ealden.race.steps;

import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.model.Racer;
import com.escanan.ealden.race.page.RacePage;
import com.escanan.ealden.race.service.RaceService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RacerSteps {
    @Autowired
    private RaceService raceService;

    private RacePage racePage;

    private Race race = null;
    private Racer racer = null;
    private Racer.SpeedType speedType = null;

    @Given("I am in a race")
    public void newRace() {
        race = raceService.newRace();
        racer = race.getRacers().get(0);
    }

    @Given("I am at position {int}")
    public void setPosition(int position) {
        racer.setPosition(position);
    }

    @Given("I have damage of {int}")
    public void setDamage(int damage) {
        racer.setDamage(damage);
    }

    @Given("I see the finish line at position {int}")
    public void setFinishLine(int finishLine) {
        race.setFinishLine(finishLine);
    }

    @When("I choose {string} speed")
    public void speed(String speed) {
        speedType = Racer.SpeedType.valueOf(speed);
    }

    @When("I roll a {int}")
    public void roll(int roll) {
        raceService.save(race);

        racePage = RacePage.load();

        racePage.roll(roll, speedType);
    }

    @When("I choose to start over in a new race")
    public void createNewRace() {
        raceService.save(race);

        racePage = RacePage.load();

        racePage.newRace();

        race = raceService.getCurrentRace();
        racer = race.getRacers().get(0);
    }

    @Then("I must now be at position {int}")
    public void assertNewPosition(int newPosition) {
        assertThat(racePage.getRacerPosition(racer), is(equalTo(newPosition)));
    }

    @Then("I must now have damage of {int}")
    public void assertNewDamage(int newDamage) {
        assertThat(racePage.getRacerDamage(racer), is(equalTo(newDamage)));
    }

    @Then("I must see the race result: WIN")
    public void assertRacerWins() {
        assertThat(racePage.isRacerAtFinishLine(racer, race.getFinishLine()), is(true));
        assertThat(racePage.isOver(), is(true));
    }

    @Then("I must see the race result: CRASHED")
    public void assertRacerCrashed() {
        assertThat(racePage.isRacerCrashed(racer), is(true));
        assertThat(racePage.isOver(), is(false));
    }

    @Then("I must see the race result: --")
    public void assertNoRaceResult() {
        assertThat(racePage.isOver(), is(false));
    }

    @Then("it's over, it's over")
    public void close() {
        RacePage.close();
    }
}
