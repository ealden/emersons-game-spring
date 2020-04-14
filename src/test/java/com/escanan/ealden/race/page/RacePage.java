package com.escanan.ealden.race.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class RacePage {
    private static final String ROOT_URL = "http://localhost:8080/";

    private WebDriver driver;

    private RacePage() {
        driver = new ChromeDriver(new ChromeOptions().addArguments("--headless"));;
        driver.navigate().to(ROOT_URL);
    }

    public static RacePage open() {
        return new RacePage();
    }

    public WebElement getHeader() {
        return driver.findElement(By.tagName("h1"));
    }
}
