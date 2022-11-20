package org.selenium.tests;

import io.restassured.http.Cookies;
import org.selenium.objects.BillingAddress;
import org.selenium.objects.Product;
import org.selenium.objects.User;
import org.selenium.pages.CheckoutPage;
import org.selenium.utils.JsonFile;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginDuringCheckoutTest extends BaseTest {
    private Product product;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUp() throws IOException {
        product = new Product(1215);

        checkoutPage = new CheckoutPage(getDriver())
                .load();
    }

    @Test
    public void shouldBeAbleToLoginDuringCheckout() {
        User user = registerNewUser();
        Cookies cookies = addProductToCart(product, 1);
        injectCookiesToBrowser(cookies);

        checkoutPage
                .load()
                .clickHereToLoginLink().getLogin()
                .login(user);

        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));
    }
}
