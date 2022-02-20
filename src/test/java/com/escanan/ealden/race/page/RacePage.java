package com.escanan.ealden.race.page;

import com.escanan.ealden.race.model.Racer;
import com.escanan.ealden.race.model.SpeedType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.ProtocolHandshake;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;
import static java.util.logging.Level.OFF;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class RacePage {
    private static final String ROOT_URL = "http://localhost:8080/";

    private final WebDriver driver;

    private final By testRoll = By.id("test-roll");
    private final By testProcessing = By.id("test-processing");

    private final By raceControls = By.id("race-controls");
    private final By raceOver = By.id("race-over");

    private final By normalSpeed = By.id("roll-normal-speed");
    private final By superSpeed = By.id("roll-super-speed");
    private final By newRace = By.id("new-race");

    private final By message = By.id("message");

    public RacePage(boolean headless) {
        reallySilenceSelenium();

        driver = createDriver(headless);
    }

    private void reallySilenceSelenium() {
        Logger.getLogger(ProtocolHandshake.class.getName()).setLevel(OFF);

        System.setErr(new PrintStream(nullOutputStream()));
    }

    private static OutputStream nullOutputStream() {
        return new OutputStream() {
            @Override
            public void write(int b) {
                // Do nothing
            }

            @Override
            public void write(byte[] b, int off, int len) {
                // Do nothing
            }
        };
    }

    public RacePage load() {
        driver.navigate().to(ROOT_URL);

        return this;
    }

    private WebDriver createDriver(boolean headless) {
        WebDriverManager.chromedriver().setup();

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
