package com.escanan.ealden.race.page;

import com.escanan.ealden.race.model.Racer;
import com.google.common.base.Joiner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.util.Arrays.asList;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementValue;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class RacePage {
    private static final String ROOT_URL = "http://localhost:8080/";
    private static final String FINISH_LINE = "WIN";
    private static final String RACER = "R";

    private static WebDriver driver;
    private WebDriverWait wait;

    private RacePage() {
        wait = new WebDriverWait(driver, 20);

        driver.navigate().to(ROOT_URL);

        waitUntilPageLoad();
    }

    public static RacePage load() {
        if (driver == null) {
            driver = createDriver();
        }

        return new RacePage();
    }

    private static WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--silent");

        return new ChromeDriver(options);
    }

    public static void close() {
        driver.quit();
    }

    public RacePage roll(int roll, Racer.SpeedType speedType) {
        getRollInput().sendKeys("" + roll);

        if (Racer.SpeedType.NORMAL == speedType) {
            getNormalSpeedButton().click();
        } else if (Racer.SpeedType.SUPER == speedType) {
            getSuperSpeedButton().click();
        }

        waitUntilNextTurn();
        waitUntilNextTurn();

        return this;
    }

    public RacePage newRace() {
        getNewRaceButton().click();

        waitUntilNextTurn();

        return this;
    }

    public int getRacerPosition(Racer racer) {
        int position = 0;

        for (int i = 1; i <= racer.getFinishLine(); i++) {
            String trackValue = getRacerPositionFieldValue(racer, i);

            if (RACER.equals(trackValue) || FINISH_LINE.equals(trackValue)) {
                position = i;

                break;
            }
        }

        return position;
    }

    public int getRacerDamage(Racer racer) {
        return Integer.parseInt(getRacerDamageField(racer).getText().trim());
    }

    public boolean isRacerAtFinishLine(Racer racer) {
        String trackValue = getRacerPositionFieldValue(racer, racer.getFinishLine());

        return FINISH_LINE.equals(trackValue);
    }

    public WebElement getRollInput() {
        return driver.findElement(By.id("roll"));
    }

    public WebElement getNormalSpeedButton() {
        return driver.findElement(By.id("roll-normal-speed"));
    }

    public WebElement getSuperSpeedButton() {
        return driver.findElement(By.id("roll-super-speed"));
    }

    public WebElement getRacerPositionField(Racer racer, int position) {
        String id = Joiner.on("-").join(asList("racer", racer.getId(), "position", position));

        return driver.findElement(By.id(id));
    }

    public WebElement getRacerDamageField(Racer racer) {
        String id = Joiner.on("-").join(asList("racer", racer.getId(), "damage"));

        return driver.findElement(By.id(id));
    }

    public WebElement getNewRaceButton() {
        return driver.findElement(By.id("new-race"));
    }

    private void waitUntilPageLoad() {
        wait.until(visibilityOf(getRollInput()));
    }

    private void waitUntilNextTurn() {
        getRollInput().sendKeys("");
        wait.until(textToBePresentInElementValue(getRollInput(), ""));
    }

    private String getRacerPositionFieldValue(Racer racer, int position) {
        return getRacerPositionField(racer, position).getText().trim();
    }
}
