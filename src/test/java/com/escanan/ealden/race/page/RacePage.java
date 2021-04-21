package com.escanan.ealden.race.page;

import com.escanan.ealden.race.model.Racer;
import com.escanan.ealden.race.model.SpeedType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Integer.parseInt;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class RacePage {
    private static final String ROOT_URL = "http://localhost:8080/";
    private static final String CRASHED = "true";

    private WebDriver driver;

    private By testRoll = By.id("test-roll");
    private By testProcessing = By.id("test-processing");

    private By raceControls = By.id("race-controls");
    private By raceOver = By.id("race-over");

    private By normalSpeed = By.id("roll-normal-speed");
    private By superSpeed = By.id("roll-super-speed");
    private By newRace = By.id("new-race");

    private By message = By.id("message");

    public RacePage(boolean headless) {
        driver = createDriver(headless);

        driver.navigate().to(ROOT_URL);
    }

    public RacePage load(boolean headless) {
        return new RacePage(headless);
    }

    private WebDriver createDriver(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--silent");

        if (headless) {
            options.addArguments("--headless");
        }

        return new ChromeDriver(options);
    }

    public void close() {
        driver.quit();
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

    private void click(By element) {
        findElement(element).click();
    }

    private void input(By element, int i) {
        input(element, "" + i);
    }

    private void input(By element, String keys) {
        findElement(element).clear();
        findElement(element).sendKeys(keys);
    }

    public int getPositionOf(Racer racer) {
        return parseInt(findTestElement(racer, "position").getText());
    }

    public int getDamageOf(Racer racer) {
        return parseInt(findTestElement(racer, "damage").getText());
    }

    public boolean hasCrashed(Racer racer) {
        return CRASHED.equals(findTestElement(racer, "crashed").getText());
    }

    public boolean isOver() {
        boolean raceControlsHidden = driver.findElements(raceControls).isEmpty();
        boolean raceOverHidden = driver.findElements(raceOver).isEmpty();

        return (raceControlsHidden && !raceOverHidden);
    }

    public String getMessage() {
        return findElement(message).getText().trim();
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

    private WebElement findTestElement(Racer racer, String key) {
        String testId = String.join("-", "test", "racer", racer.getId().toString(), key);

        return findElement(By.id(testId));
    }
}
