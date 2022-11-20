package org.selenium.tests;

import org.selenium.tests.dataproviders.DataProviders;
import org.selenium.pages.CartPage;
import org.selenium.pages.HomePage;
import org.selenium.pages.StorePage;
import org.selenium.objects.Product;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CartTest extends BaseTest {

    @Test
    public void shouldBeAbleToAddProductToCart() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver()).load()
                .getProductThumbnail().clickAddToCartButton(product.getName())
                .clickViewCart();

        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }

    @Test
    public void shouldBeAbleToAddMultipleProductsToCart() {
        int numberOfProductsToBeAdded = 3;
        int numberOfProductsInCart = new StorePage(getDriver()).load()
                .getProductThumbnail().addMultipleRandomProductsToCart(numberOfProductsToBeAdded)
                .getHeader().getNumberOfProductsInCart();

        Assert.assertEquals(numberOfProductsInCart, numberOfProductsToBeAdded);
    }

    @Test(dataProvider = "getFeaturedProducts", dataProviderClass = DataProviders.class)
    public void shouldBeAbleToAddToCartFeaturedProducts(String productName) {
        CartPage cartPage = new HomePage(getDriver()).load()
                .getProductThumbnail().clickAddToCartButton(productName)
                .clickViewCart();

        Assert.assertEquals(cartPage.getProductName(), productName);
    }
}
