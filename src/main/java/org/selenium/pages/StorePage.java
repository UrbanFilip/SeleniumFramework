package org.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.base.BasePage;
import org.selenium.pages.components.ProductThumbnail;


public class StorePage extends BasePage {
    @FindBy(id = "woocommerce-product-search-field-0")
    private WebElement searchField;
    @FindBy(css = "button[value='Search']")
    private WebElement searchButton;
    @FindBy(css = ".woocommerce-products-header")
    private WebElement title;
    @FindBy(css = "p[class='woocommerce-info']")
    private WebElement info;

    private ProductThumbnail productThumbnail;

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }


    public StorePage(WebDriver driver) {
        super(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    public StorePage load() {
        load("/store");
        return this;
    }

    public StorePage search(String text) {
        enterTextInSearchField(text).clickSearchButton();
        wait.until(ExpectedConditions.urlContains("product"));

        return this;
    }

    private StorePage enterTextInSearchField(String text) {
        waitForElementToBeVisible(searchField).sendKeys(text);
        return this;
    }

    private StorePage clickSearchButton() {
        waitForElementToBeClickable(searchButton).click();
        return this;
    }

    public String getTitle() {
        return waitForElementToBeVisible(title).getText();
    }

    public String getInfo() {
        return waitForElementToBeVisible(info).getText();
    }

    public boolean isProductWithSearchedKeywordVisible(String searchedProduct) {
        boolean isProductName = false;
        for (WebElement productName: productThumbnail.getProductNames()) {
            if (productName.getText().contains(searchedProduct)) {
                isProductName = true;
            } else {
                return false;
            }
        }
        return isProductName;
    }

}
