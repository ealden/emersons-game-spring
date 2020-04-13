package gradle.cucumber;

import com.escanan.ealden.race.Race;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class RaceSteps {
    private Race race = null;
    private Race.SpeedType speedType = null;

    @Given("I am in a race")
    public void newGame() {
        race = new Race();
    }

    @Given("I am at position {int}")
    public void setPosition(int position) {
        race.setPosition(position);
    }

    @Given("I have damage of {int}")
    public void setDamage(int damage) {
        race.setDamage(damage);
    }

    @When("I choose {string} speed")
    public void speed(String speed) {
        speedType = Race.SpeedType.valueOf(speed);
    }

    @When("I roll a {int}")
    public void roll(int roll) {
        race.move(roll, speedType);
    }

    @Then("I must be at position {int}")
    public void assertNewPosition(int newPosition) {
        assertThat(race.getPosition(), is(equalTo(newPosition)));
    }

    @Then("I must now have damage of {int}")
    public void assertNewDamage(int newDamage) {
        assertThat(race.getDamage(), is(equalTo(newDamage)));
    }

    @Then("I must see the race result: {string}")
    public void assertResult(String result) {
        var over = "WIN".equals(result);

        assertThat(race.isOver(), is(over));
    }
}
