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
        getRollInput().sendKeys("" + roll);

        if (Racer.SpeedType.NORMAL == speedType) {
            getNormalSpeedButton().click();
        } else if (Racer.SpeedType.SUPER == speedType) {
            getSuperSpeedButton().click();
        }

        waitUntilReady();

        return this;
    }

    public RacePage newRace() {
        getNewRaceButton().click();

        waitUntilReady();

        return this;
    }

    public int getRacerPosition(Racer racer) {
        return parseInt(getRacerPositionInput(racer).getText());
    }

    public int getRacerDamage(Racer racer) {
        return parseInt(getRacerDamageInput(racer).getText());
    }

    public boolean isRacerAtFinishLine(Racer racer) {
        return (racer.getFinishLine() == getRacerPosition(racer));
    }

    public WebElement getRollInput() {
        return doWait().until(visibilityOfElementLocated(By.id("test-roll")));
    }

    public WebElement getReadyInput() {
        return doWait().until(visibilityOfElementLocated(By.id("test-ready")));
    }

    public WebElement getNormalSpeedButton() {
        return driver.findElement(By.id("roll-normal-speed"));
    }

    public WebElement getSuperSpeedButton() {
        return driver.findElement(By.id("roll-super-speed"));
    }

    public WebElement getRacerPositionInput(Racer racer) {
        String id = Joiner.on("-").join(asList("test", "racer", racer.getId(), "position"));

        return driver.findElement(By.id(id));
    }

    public WebElement getRacerDamageInput(Racer racer) {
        String id = Joiner.on("-").join(asList("test", "racer", racer.getId(), "damage"));

        return driver.findElement(By.id(id));
    }

    public WebElement getNewRaceButton() {
        return driver.findElement(By.id("new-race"));
    }

    private void waitUntilReady() {
        doWait().until(textToBePresentInElementValue(getReadyInput(), "true"));
    }

    private WebDriverWait doWait() {
        return new WebDriverWait(driver, 20);
    }
}
