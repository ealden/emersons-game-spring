package com.escanan.ealden.race.steps;

import com.escanan.ealden.race.model.Race;
import com.escanan.ealden.race.model.Racer;
import com.escanan.ealden.race.model.SpeedType;
import com.escanan.ealden.race.page.RacePage;
import com.escanan.ealden.race.service.RaceService;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static com.escanan.ealden.race.model.Racer.MAX_DAMAGE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RacerSteps {
    private static final String YES = "YES";

    @Autowired
    private RaceService raceService;

    @Value("${race.test.headless}")
    private boolean headless;

    private RacePage page;

    private Race race;
    private Racer racer;
    private SpeedType speedType;

    @After
    public void tearDown() {
        if (headless) {
            page.close();
        }
    }

    @Given("I am in a race")
    public void newRace() {
        race = raceService.newRace();
        racer = race.getRacers().get(0);

        page = new RacePage(headless);
        page.load();
    }

    @Given("I am at position {int}")
    public void setPosition(int position) {
        racer.setPosition(position);

        raceService.save(race);
    }

    @Given("I have damage of {int}")
    public void setDamage(int damage) {
        racer.setDamage(damage);

        raceService.save(race);
    }

    @Given("I see the finish line at position {int}")
    public void setFinishLine(int finishLine) {
        race.setFinishLine(finishLine);

        raceService.save(race);
    }

    @When("I choose {string} speed")
    public void speed(String speed) {
        speedType = SpeedType.valueOf(speed);
    }

    @When("I roll a {int}")
    public void roll(int roll) {
        loadRace();

        page.roll(roll, speedType);

        race = raceService.getCurrentRace();
    }

    private void roll(int roll, SpeedType speedType) {
        this.speedType = speedType;

        roll(roll);
    }

    @When("I choose to start over in a new race")
    public void createNewRace() {
        loadRace();

        page.newRace();

        race = raceService.getCurrentRace();
        racer = race.getRacers().get(0);
    }

    @When("all racers have crashed!")
    public void racersCrashed() {
        roll(1, SpeedType.NORMAL);

        for (Racer racer : race.getRacers()) {
            racer.setDamage(MAX_DAMAGE);
        }

        raceService.save(race);

        page.load();
    }

    @When("I try to view the race")
    public void loadRace() {
    }

    @Then("I must now be at position {int}")
    public void assertNewPosition(int newPosition) {
        assertThat(page.getPositionOf(racer), is(equalTo(newPosition)));
    }

    @Then("I must now have damage of {int}")
    public void assertNewDamage(int newDamage) {
        assertThat(page.getDamageOf(racer), is(equalTo(newDamage)));
    }

    @Then("I must see the race result: WIN")
    public void assertRacerWins() {
        assertThat(page.getPositionOf(racer), is(equalTo(race.getFinishLine())));
        assertThat(page.isOver(), is(true));
    }

    @Then("I must see the race result: CRASHED")
    public void assertRacerCrashed() {
        assertThat(page.hasCrashed(racer), is(true));
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
        assertThat(race.getLastRoll().getNumber(), is(equalTo(roll)));
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

    @Then("Crashed: {string}")
    public void assertRollWithCrashedLogged(String crashed) {
        boolean didCrash = YES.equals(crashed);

        assertThat(race.getLastRoll().isCrashed(), is(equalTo(didCrash)));
    }

    @Then("Win: {string}")
    public void assertRollWithWinLogged(String win) {
        boolean didWin = YES.equals(win);

        assertThat(race.getLastRoll().isWin(), is(equalTo(didWin)));
    }

    @Then("I must see the message: {string}")
    public void assertMessage(String message) {
        assertThat(page.getMessage(), is(equalTo(message)));
    }

    @Then("our race must be over!")
    public void assertRacersCrashedAndBurned() {
        assertThat(page.isOver(), is(true));
    }

    @Then("I must see the race result: --")
    public void assertNoRaceResult() {
        assertThat(page.isOver(), is(false));
    }
}
