package gradle.cucumber;

import com.escanan.ealden.race.Game;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class RaceSteps {
    private Game game = null;
    private Game.SpeedType speedType = null;

    @Given("I am in a race")
    public void newGame() {
        game = new Game();
    }

    @Given("I am at position {int}")
    public void setPosition(int position) {
        game.setPosition(position);
    }

    @Given("I have damage of {int}")
    public void setDamage(int damage) {
        game.setDamage(damage);
    }

    @Given("I set my speed to {string}")
    public void setSpeed(String speed) {
        speedType = Game.SpeedType.valueOf(speed);
    }

    @When("I roll a {int}")
    public void roll(int roll) {
        game.move(roll, speedType);
    }

    @Then("I must be at position {int}")
    public void assertNewPosition(int newPosition) {
        assertThat(game.getPosition(), is(equalTo(newPosition)));
    }

    @Then("I must now have damage of {int}")
    public void assertNewDamage(int newDamage) {
        assertThat(game.getDamage(), is(equalTo(newDamage)));
    }

    @Then("result of the race is {string}")
    public void assertResult(String result) {
        var over = "WIN".equals(result);

        assertThat(game.isOver(), is(over));
    }
}
