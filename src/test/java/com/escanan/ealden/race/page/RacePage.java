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
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class RacePage {
    private static final String ROOT_URL = "http://localhost:8080/";
    private static final String CRASHED = "true";

    private WebDriver driver;

    private boolean headless;

    private By testRoll = id("test-roll");
    private By testProcessing = id("test-processing");

    private By raceControls = id("race-controls");
    private By raceOver = id("race-over");

    private By rollNormalSpeed = id("roll-normal-speed");
    private By rollSuperSpeed = id("roll-super-speed");
    private By newRace = id("new-race");

    private By message = id("message");

    private RacePage(boolean headless) {
        this.headless = headless;

        driver = createDriver(headless);

        driver.navigate().to(ROOT_URL);
    }

    public static RacePage load(boolean headless) {
        return new RacePage(headless);
    }

    private static WebDriver createDriver(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--silent");

        if (headless) {
            options.addArguments("--headless");
        }

        return new ChromeDriver(options);
    }

    public void close() {
        if (headless) {
            driver.quit();
        }
    }

    public RacePage roll(int roll, SpeedType speedType) {
        getRollField().clear();
        getRollField().sendKeys("" + roll);

        if (SpeedType.NORMAL == speedType) {
            click(rollNormalSpeed);
        } else if (SpeedType.SUPER == speedType) {
            click(rollSuperSpeed);
        }

        waitUntilProcessingComplete();

        return this;
    }

    public RacePage newRace() {
        click(newRace);

        waitUntilProcessingComplete();

        return this;
    }

    private void click(By element) {
        findElement(element).click();
    }

    public int positionOf(Racer racer) {
        return parseInt(getRacerPositionField(racer).getText());
    }

    public int damageOf(Racer racer) {
        return parseInt(getRacerDamageField(racer).getText());
    }

    public boolean isRacerAtFinishLine(Racer racer, int finishLine) {
        return (positionOf(racer) == finishLine);
    }

    public boolean isRacerCrashed(Racer racer) {
        return CRASHED.equals(getRacerCrashedField(racer).getText());
    }

    public boolean isOver() {
        boolean raceControlsHidden = driver.findElements(raceControls).isEmpty();
        boolean raceOverHidden = driver.findElements(raceOver).isEmpty();

        return (raceControlsHidden && !raceOverHidden);
    }

    public String getMessage() {
        return getMessageField().getText().trim();
    }

    private WebElement getRollField() {
        return findElement(testRoll);
    }

    private WebElement getRacerPositionField(Racer racer) {
        return findElement(test(racer, "position"));
    }

    private WebElement getRacerDamageField(Racer racer) {
        return findElement(test(racer, "damage"));
    }

    private WebElement getRacerCrashedField(Racer racer) {
        return findElement(test(racer, "crashed"));
    }

    private WebElement getMessageField() {
        return findElement(message);
    }

    private void waitUntilProcessingComplete() {
        doWait().until(invisibilityOfElementLocated(testProcessing));
    }

    private WebDriverWait doWait() {
        return new WebDriverWait(driver, 20);
    }

    private WebElement findElement(By by) {
        return doWait().until(visibilityOfElementLocated(by));
    }

    private By test(Racer racer, String key) {
        return id(Joiner.on("-").join(asList("test", "racer", racer.getId(), key)));
    }
}
