package com.escanan.ealden.race.page;

import com.escanan.ealden.race.model.Racer;
import com.escanan.ealden.race.model.SpeedType;
import com.google.common.base.Joiner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementValue;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class RacePage {
    private static final String ROOT_URL = "http://localhost:8080/";
    private static final boolean HEADLESS = true;
    private static final String CRASHED = "true";

    private static WebDriver driver;

    private RacePage() {
        driver.navigate().to(ROOT_URL);
    }

    public static RacePage load() {
        if (driver == null) {
            driver = createDriver();
        }

        return new RacePage();
    }

    private static WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--silent");

        if (HEADLESS) {
            options.addArguments("--headless");
        }

        return new ChromeDriver(options);
    }

    public static void close() {
        if (HEADLESS) {
            driver.quit();
        }
    }

    public RacePage roll(int roll, SpeedType speedType) {
        getRollField().clear();
        getRollField().sendKeys("" + roll);

        if (SpeedType.NORMAL == speedType) {
            getNormalSpeedButton().click();
        } else if (SpeedType.SUPER == speedType) {
            getSuperSpeedButton().click();
        }

        waitUntilProcessingComplete();

        return this;
    }

    public RacePage newRace() {
        getNewRaceButton().click();

        waitUntilProcessingComplete();

        return this;
    }

    public int getRacerPosition(Racer racer) {
        return parseInt(getRacerPositionField(racer).getText());
    }

    public int getRacerDamage(Racer racer) {
        return parseInt(getRacerDamageField(racer).getText());
    }

    public boolean isRacerAtFinishLine(Racer racer, int finishLine) {
        return (getRacerPosition(racer) == finishLine);
    }

    public boolean isRacerCrashed(Racer racer) {
        return CRASHED.equals(getRacerCrashedField(racer).getText());
    }

    public boolean isOver() {
        boolean raceControlsHidden = driver.findElements(By.id("race-controls")).isEmpty();
        boolean raceOverHidden = driver.findElements(By.id("race-over")).isEmpty();

        return (raceControlsHidden && !raceOverHidden);
    }

    public String getMessage() {
        return getMessageField().getText().trim();
    }

    private WebElement getRollField() {
        return findElementById("test-roll");
    }

    private WebElement getProcessingField() {
        return findElementById("test-processing");
    }

    private WebElement getNormalSpeedButton() {
        return findElementById("roll-normal-speed");
    }

    private WebElement getSuperSpeedButton() {
        return findElementById("roll-super-speed");
    }

    private WebElement getRacerPositionField(Racer racer) {
        return findElementById(test(racer, "position"));
    }

    private WebElement getRacerDamageField(Racer racer) {
        return findElementById(test(racer, "damage"));
    }

    private WebElement getRacerCrashedField(Racer racer) {
        return findElementById(test(racer, "crashed"));
    }

    private WebElement getNewRaceButton() {
        return findElementById("new-race");
    }

    private WebElement getMessageField() {
        return findElementById("message");
    }

    private void waitUntilProcessingComplete() {
        doWait().until(textToBePresentInElementValue(getProcessingField(), "false"));
    }

    private WebDriverWait doWait() {
        return new WebDriverWait(driver, 20);
    }

    private WebElement findElement(By by) {
        return doWait().until(visibilityOfElementLocated(by));
    }

    private WebElement findElementById(String id) {
        return findElement(By.id(id));
    }

    private String test(Racer racer, String key) {
        return Joiner.on("-").join(asList("test", "racer", racer.getId(), key));
    }
}
