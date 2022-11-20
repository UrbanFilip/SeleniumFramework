package org.selenium.pages;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.base.BasePage;
import org.selenium.pages.components.Header;
import org.selenium.pages.components.ProductThumbnail;

public class HomePage extends BasePage {
    private Header header;
    private ProductThumbnail productThumbnail;
    public HomePage(WebDriver driver) {
        super(driver);
        header = new Header(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    public Header getHeader() {
        return header;
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public HomePage load() {
        load("/");
        wait.until(ExpectedConditions.titleContains("AskOmDch"));

        return this;
    }
}
