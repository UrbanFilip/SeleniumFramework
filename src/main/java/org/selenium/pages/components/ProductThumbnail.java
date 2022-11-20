package org.selenium.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.selenium.base.BasePage;
import org.selenium.pages.CartPage;

import java.util.List;
import java.util.Random;

public class ProductThumbnail extends BasePage {
    private Header header;
    @FindBy(css = "a[title='View cart']")
    private WebElement viewCartLink;
    @FindBy(css = ".woocommerce-loop-product__title")
    private List<WebElement> productNames;
    @FindBy(css = ".add_to_cart_button")
    private List<WebElement> addToCartButtons;
    @FindBy(css = ".add_to_cart_button.loading")
    private List<WebElement> addToCartLoaders;

    public ProductThumbnail(WebDriver driver) {
        super(driver);
        this.header = new Header(driver);
    }

    public Header getHeader() {
        return header;
    }

    private WebElement getAddToCartButtonElement(String productName) {
        return driver.findElement(By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']"));
    }

    public ProductThumbnail clickAddToCartButton(String productName) {
        WebElement addToCartButton = getAddToCartButtonElement(productName);
        waitForElementToBeClickable(addToCartButton).click();
        return this;
    }

    private ProductThumbnail addRandomProductToCart() {
        int randomProduct = new Random().nextInt(addToCartButtons.size() - 1);
        int numberOfProductsInCart = header.getNumberOfProductsInCart();
        waitForElementToBeClickable(addToCartButtons.get(randomProduct)).click();
        wait.until((ExpectedCondition<Boolean>) driver ->
                header.getNumberOfProductsInCart() > numberOfProductsInCart);

        return this;
    }

    public ProductThumbnail addMultipleRandomProductsToCart(int numberOfProductsToBeAdded) {
        waitForElementsToBeVisible(addToCartButtons);
        int counter = 0;
        while (counter < numberOfProductsToBeAdded) {
            addRandomProductToCart();

            counter++;
        }

        waitForOverlay(addToCartLoaders);
        return this;
    }


    public CartPage clickViewCart() {
        waitForElementToBeClickable(viewCartLink).click();
        return new CartPage(driver);
    }

    public List<WebElement> getProductNames() {
        return waitForElementsToBeVisible(productNames);
    }
}
