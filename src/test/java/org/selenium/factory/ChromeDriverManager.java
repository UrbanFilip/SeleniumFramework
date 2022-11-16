package org.selenium.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.selenium.utils.PropertyReader;

public class ChromeDriverManager implements DriverManager {
    @Override
    public WebDriver createDriver() {
        WebDriverManager.chromedriver().cachePath("drivers").setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        if (PropertyReader.HEADLESS.getProperty().equalsIgnoreCase("true")) {
            chromeOptions.addArguments("headless");
        }

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.manage().window().maximize();

        return driver;
    }

}
