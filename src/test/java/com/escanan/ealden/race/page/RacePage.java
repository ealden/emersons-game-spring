package com.escanan.ealden.race.page;

import com.escanan.ealden.race.model.Racer;
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

    public RacePage roll(int roll, Racer.SpeedType speedType) {
        getRollField().sendKeys("" + roll);

        if (Racer.SpeedType.NORMAL == speedType) {
            getNormalSpeedButton().click();
        } else if (Racer.SpeedType.SUPER == speedType) {
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

    private WebElement getRollField() {
        return doWait().until(visibilityOfElementLocated(By.id("test-roll")));
    }

    private WebElement getProcessingField() {
        return doWait().until(visibilityOfElementLocated(By.id("test-processing")));
    }

    private WebElement getNormalSpeedButton() {
        return driver.findElement(By.id("roll-normal-speed"));
    }

    private WebElement getSuperSpeedButton() {
        return driver.findElement(By.id("roll-super-speed"));
    }

    private WebElement getRacerPositionField(Racer racer) {
        String id = Joiner.on("-").join(asList("test", "racer", racer.getId(), "position"));

        return driver.findElement(By.id(id));
    }

    private WebElement getRacerDamageField(Racer racer) {
        String id = Joiner.on("-").join(asList("test", "racer", racer.getId(), "damage"));

        return driver.findElement(By.id(id));
    }

    private WebElement getRacerCrashedField(Racer racer) {
        String id = Joiner.on("-").join(asList("test", "racer", racer.getId(), "crashed"));

        return driver.findElement(By.id(id));
    }

    private WebElement getNewRaceButton() {
        return driver.findElement(By.id("new-race"));
    }

    private void waitUntilProcessingComplete() {
        doWait().until(textToBePresentInElementValue(getProcessingField(), "false"));
    }

    private WebDriverWait doWait() {
        return new WebDriverWait(driver, 20);
    }
}
