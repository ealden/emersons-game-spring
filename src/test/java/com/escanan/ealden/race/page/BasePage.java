package com.escanan.ealden.race.page;

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
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.OFF;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class BasePage {
    private final WebDriver driver;

    private final By testProcessing = By.id("test-processing");

    public BasePage(boolean headless) {
        reallySilenceSelenium();

        driver = createDriver(headless);
    }

    public void close() {
        driver.quit();
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

    private WebDriver createDriver(boolean headless) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--silent");

        if (headless) {
            options.addArguments("--headless");
        }

        return new ChromeDriver(options);
    }

    protected void navigateTo(String url) {
        driver.navigate().to(url);
    }

    protected void click(By element) {
        findElement(element).click();
    }

    protected void input(By element, int i) {
        input(element, "" + i);
    }

    protected void input(By element, String keys) {
        findElement(element).clear();
        findElement(element).sendKeys(keys);
    }

    protected void waitUntilProcessingComplete() {
        doWait().until(invisibilityOfElementLocated(testProcessing));
    }

    protected WebDriverWait doWait() {
        return new WebDriverWait(driver, 20);
    }

    protected WebElement findElement(By by) {
        return doWait().until(visibilityOfElementLocated(by));
    }

    protected WebElement findTestElement(Long id, String key) {
        String testId = String.join("-", "test", "racer", id.toString(), key);

        return findElement(By.id(testId));
    }

    protected List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }
}
