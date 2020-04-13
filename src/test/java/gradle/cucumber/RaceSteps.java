package gradle.cucumber;

import com.escanan.ealden.race.Game;
import io.cucumber.java8.En;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class RaceSteps implements En {
    private Game game = null;

    public RaceSteps() {
        When("I create a new game", () -> {
            game = new Game();
        });

        Then("I must have a game to play", () -> {
            assertThat(game, is(notNullValue()));
        });
    }
}
