package com.escanan.ealden.race.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RacePage {
    private static final String ROOT_URL = "http://localhost:8080/";

    private WebDriver driver = WebDrivers.instance();

    private RacePage() {
        driver.navigate().to(ROOT_URL);
    }

    public static RacePage open() {
        return new RacePage();
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
}
