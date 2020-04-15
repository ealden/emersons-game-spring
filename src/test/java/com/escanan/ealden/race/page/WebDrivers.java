package com.escanan.ealden.race.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDrivers {
    private static WebDriver driver;

    public static WebDriver instance() {
        if (driver == null) {
            var options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--silent");

            driver = new ChromeDriver(options);
        }

        return driver;
    }
}
