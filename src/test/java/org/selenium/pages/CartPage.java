package org.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.selenium.base.BasePage;

public class CartPage extends BasePage {
    @FindBy(css = ".product-name a")
    private WebElement productName;
    @FindBy(css = ".checkout-button")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return waitForElementToBeVisible(productName).getText();
    }

    public CheckoutPage checkout() {
        waitForElementToBeClickable(checkoutButton).click();

        return new CheckoutPage(driver);
    }
}
