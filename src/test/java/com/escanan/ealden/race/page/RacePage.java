package com.escanan.ealden.race.page;

import com.escanan.ealden.race.model.Racer;
import com.escanan.ealden.race.model.SpeedType;
import org.openqa.selenium.By;

import static java.lang.Integer.parseInt;

public class RacePage extends BasePage {
    private static final String ROOT_URL = "http://localhost:8080/";

    private final By testRoll = By.id("test-roll");

    private final By raceControls = By.id("race-controls");
    private final By raceOver = By.id("race-over");

    private final By normalSpeed = By.id("roll-normal-speed");
    private final By superSpeed = By.id("roll-super-speed");
    private final By newRace = By.id("new-race");

    private final By message = By.id("message");

    public RacePage(boolean headless) {
        super(headless);
    }

    public RacePage load() {
        navigateTo(ROOT_URL);

        return this;
    }

    public RacePage roll(int roll, SpeedType speedType) {
        input(testRoll, roll);

        if (SpeedType.NORMAL == speedType) {
            click(normalSpeed);
        } else if (SpeedType.SUPER == speedType) {
            click(superSpeed);
        }

        waitUntilProcessingComplete();

        return this;
    }

    public RacePage newRace() {
        click(newRace);

        waitUntilProcessingComplete();

        return this;
    }

    public int getPositionOf(Racer racer) {
        return parseInt(findTestElement(racer.getId(), "position").getText());
    }

    public int getDamageOf(Racer racer) {
        return parseInt(findTestElement(racer.getId(), "damage").getText());
    }

    public boolean isOver() {
        boolean raceControlsHidden = findElements(raceControls).isEmpty();
        boolean raceOverHidden = findElements(raceOver).isEmpty();

        return (raceControlsHidden && !raceOverHidden);
    }

    public String getMessage() {
        return findElement(message).getText().trim();
    }
}
