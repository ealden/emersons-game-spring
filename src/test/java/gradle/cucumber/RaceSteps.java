package gradle.cucumber;

import com.escanan.ealden.race.Game;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class RaceSteps {
    private Game game = null;

    @When("I create a new game")
    public void newGame() {
       game = new Game();
    }

    @Then("I must have a game to play")
    public void assertGameNotNull() {
        assertThat(game, notNullValue());
    }
}