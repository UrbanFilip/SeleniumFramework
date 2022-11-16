package org.selenium.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.utils.PropertyReader;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void load(String endpoint) {
        driver.get(PropertyReader.BASE_URL.getProperty() + endpoint);
    }

    public void waitForOverlay(List<WebElement> element) {
        if (element.size() > 0) {
            System.out.println("Overlays size: " + element.size());
            wait.until(ExpectedConditions.invisibilityOfAllElements(element));

            System.out.println("Overlays are invisible.");
        }
    }

    public WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public List<WebElement> waitForElementsToBeVisible(List<WebElement> element) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }
}
