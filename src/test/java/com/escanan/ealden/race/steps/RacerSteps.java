package com.escanan.ealden.race.steps;

import com.escanan.ealden.race.data.RacerRepository;
import com.escanan.ealden.race.model.Racer;
import com.escanan.ealden.race.page.RacePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RacerSteps {
    @Autowired
    private RacerRepository racerRepository;
    private RacePage racePage;

    private Racer racer = null;
    private Racer.SpeedType speedType = null;

    @Given("I am in a race")
    public void newRace() {
        racer = new Racer("Racer 1");
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
        racer.setFinishLine(finishLine);
    }

    @When("it is my turn to roll")
    public void nextRacer() {
        racerRepository.save(racer);

        racePage = RacePage.load();
    }

    @When("I choose {string} speed")
    public void speed(String speed) {
        speedType = Racer.SpeedType.valueOf(speed);
    }

    @When("I roll a {int}")
    public void roll(int roll) {
        racePage.roll(roll, speedType);
    }

    @When("I choose to start over in a new race")
    public void createNewRace() {
        racerRepository.save(racer);

        racePage = RacePage.load();

        racePage.newRace();
    }

    @When("I join in as a new racer")
    public void joinRace() {
        racer = racerRepository.findAll().iterator().next();
    }

    @Then("I must now be at position {int}")
    public void assertNewPosition(int newPosition) {
        assertThat(racePage.getRacerPosition(racer), is(equalTo(newPosition)));
    }

    @Then("I must now have damage of {int}")
    public void assertNewDamage(int newDamage) {
        assertThat(racePage.getRacerDamage(racer), is(equalTo(newDamage)));
    }

    @Then("I must see the race result: {string}")
    public void assertResult(String result) {
        boolean over = "WIN".equals(result);

        assertThat(racePage.isRacerAtFinishLine(racer), is(over));
    }
}
