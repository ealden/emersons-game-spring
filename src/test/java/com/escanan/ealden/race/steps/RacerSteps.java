package com.escanan.ealden.race.steps;

import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.model.Racer;
import com.escanan.ealden.race.model.Racer.SpeedType;
import com.escanan.ealden.race.page.RacePage;
import com.escanan.ealden.race.service.RaceService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static com.escanan.ealden.race.model.Racer.MAX_DAMAGE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.sameInstance;

public class RacerSteps {
    @Autowired
    private RaceService raceService;

    private RacePage racePage;

    private Race race;
    private Racer racer;
    private SpeedType speedType;

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
        speedType = SpeedType.valueOf(speed);
    }

    @When("I roll a {int}")
    public void roll(int roll) {
        raceService.save(race);

        racePage = RacePage.load();

        racePage.roll(roll, speedType);

        race = raceService.getCurrentRace();
    }

    @When("I choose to start over in a new race")
    public void createNewRace() {
        raceService.save(race);

        racePage = RacePage.load();

        racePage.newRace();

        race = raceService.getCurrentRace();
        racer = race.getRacers().get(0);
    }

    @When("all racers have crashed!")
    public void racersCrashed() {
        for (Racer racer : race.getRacers()) {
            racer.setDamage(MAX_DAMAGE);
        }

        raceService.save(race);

        racePage = RacePage.load();
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

    @Then("I must now have a log entry with the following:")
    public void assertRollWithRacerLogged() {
        Racer previousRacer = race.getRacers().get(0);

        assertThat(race.getLastRoll().getRacer(), is(sameInstance(previousRacer)));
    }

    @Then("Position: {int}")
    public void assertRollWithPositionLogged(int position) {
        assertThat(race.getLastRoll().getPosition(), is(equalTo(position)));
    }

    @Then("Damage: {int}")
    public void assertRollWithDamageLogged(int damage) {
        assertThat(race.getLastRoll().getDamage(), is(equalTo(damage)));
    }

    @Then("Speed: {string}")
    public void assertRollWithSpeedTypeLogged(String speedType) {
        assertThat(race.getLastRoll().getSpeedType(), is(equalTo(SpeedType.valueOf(speedType))));
    }

    @Then("Roll: {int}")
    public void assertRollWithRollLogged(int roll) {
        assertThat(race.getLastRoll().getRoll(), is(equalTo(roll)));
    }

    @Then("Move: {int}")
    public void assertRollWithMoveLogged(int move) {
        assertThat(race.getLastRoll().getMove(), is(equalTo(move)));
    }

    @Then("New Position: {int}")
    public void assertRollWithNewPositionLogged(int newPosition) {
        assertThat(race.getLastRoll().getNewPosition(), is(equalTo(newPosition)));
    }

    @Then("New Damage: {int}")
    public void assertRollWithNewDamageLogged(int newDamage) {
        assertThat(race.getLastRoll().getNewDamage(), is(equalTo(newDamage)));
    }

    @Then("our race must be over!")
    public void assertRacersCrashedAndBurned() {
        assertThat(racePage.isOver(), is(true));
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
