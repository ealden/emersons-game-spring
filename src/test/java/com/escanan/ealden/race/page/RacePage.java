package com.escanan.ealden.race.page;

import com.escanan.ealden.race.model.Racer;
import com.google.common.base.Joiner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.util.Arrays.asList;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementValue;

public class RacePage {
    private static final String ROOT_URL = "http://localhost:8080/";
    private static final String FINISH_LINE = "WIN";
    private static final String RACER = "R";

    private WebDriver driver = WebDrivers.instance();
    private WebDriverWait wait = new WebDriverWait(driver, 20);

    private RacePage() {
        driver.navigate().to(ROOT_URL);
    }

    public static RacePage load() {
        return new RacePage();
    }

    public void close() {
        WebDrivers.quit();
    }

    public RacePage roll(int roll, Racer.SpeedType speedType) {
        if (Racer.SpeedType.NORMAL == speedType) {
            rollNormalSpeed(roll);
        } else if (Racer.SpeedType.SUPER == speedType) {
            rollSuperSpeed(roll);
        }

        return this;
    }

    public RacePage rollNormalSpeed(int roll) {
        getRollInput().sendKeys("" + roll);

        getNormalSpeedButton().click();

        waitUntilNextTurn();

        return this;
    }

    public RacePage rollSuperSpeed(int roll) {
        getRollInput().sendKeys("" + roll);

        getSuperSpeedButton().click();

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

    private void waitUntilNextTurn() {
        wait.until(textToBePresentInElementValue(getRollInput(), ""));
    }

    private String getRacerPositionFieldValue(Racer racer, int position) {
        return getRacerPositionField(racer, position).getText().trim();
    }
}
