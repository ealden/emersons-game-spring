package com.escanan.ealden.race.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RacePage {
    private WebDriver driver;
    private int port;

    public RacePage(WebDriver driver, int port) {
        this.driver = driver;
        this.port = port;
    }

    public RacePage open() {
        driver.navigate().to(String.format("http://localhost:%s/", port));

        return this;
    }

    public WebElement getHeader() {
        return driver.findElement(By.tagName("h1"));
    }
}
