package org.selenium.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.selenium.utils.PropertyReader;

public class FirefoxDriverManager implements DriverManager {

    @Override
    public WebDriver createDriver() {
        WebDriverManager.firefoxdriver().cachePath("drivers").setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        if (PropertyReader.HEADLESS.getProperty().equalsIgnoreCase("true")) {
            firefoxOptions.addArguments("--headless");
        }

        WebDriver driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().maximize();

        return driver;
    }

}
