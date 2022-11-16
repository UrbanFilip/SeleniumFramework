package org.selenium.pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.selenium.base.BasePage;
import org.selenium.pages.AccountPage;
import org.selenium.pages.StorePage;

public class Header extends BasePage {
    @FindBy(css = "#menu-item-1227 a")
    private WebElement storeMenuLink;

    @FindBy(css = "#menu-item-1237")
    private WebElement accountLink;

    @FindBy(css = "#ast-desktop-header span[class='count']")
    private WebElement numberOfProducts;

    public Header(WebDriver driver) {
        super(driver);
    }

    public StorePage navigateToStoreUsingMenu() {
        waitForElementToBeClickable(storeMenuLink).click();

        return new StorePage(driver);
    }

    public AccountPage navigateToAccountPageUsingMenu() {
        waitForElementToBeClickable(storeMenuLink).click();

        return new AccountPage(driver);
    }

    public int getNumberOfProductsInCart() {
        return Integer.parseInt(numberOfProducts.getText());
    }
}
