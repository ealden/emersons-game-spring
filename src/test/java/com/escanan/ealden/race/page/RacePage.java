package com.escanan.ealden.race.page;

import com.escanan.ealden.race.model.Racer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class RacePage {
    private static final String ROOT_URL = "http://localhost:8080/";

    private WebDriver driver = WebDrivers.instance();
    private WebDriverWait wait = new WebDriverWait(driver, 20);

    private RacePage() {
        driver.navigate().to(ROOT_URL);

        wait.until(visibilityOf(getHeader()));
    }

    public static RacePage open() {
        return new RacePage();
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

    public WebElement getHeader() {
        return driver.findElement(By.tagName("h1"));
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

    private void waitUntilNextTurn() {
        wait.until(textToBePresentInElementValue(getRollInput(), ""));
    }
}
