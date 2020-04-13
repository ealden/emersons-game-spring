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
    @When("I create a new game")
    public void newGame() {
        game = new Game();
    }

    @Then("I must have a game to play")
    public void assertGameNotNull() {
        assertThat(game, is(notNullValue()));
    }

    @When("I set my speed to NORMAL")
    public void normalSpeed() {
        speedType = Game.SpeedType.NORMAL;
    }

    @When("I set my speed to SUPER")
    public void superSpeed() {
        speedType = Game.SpeedType.SUPER;
    }

    @When("I roll a {int}")
    public void roll(Integer roll) {
        game.move(roll, speedType);
    }

    @Then("I must be at position {int}")
    public void assertNewPosition(Integer newPosition) {
        assertThat(game.getPosition(), is(equalTo(newPosition)));
    }
}
