package org.selenium.tests;

import io.restassured.http.Cookies;

import org.selenium.base.BaseTest;
import org.selenium.pages.CheckoutPage;
import org.selenium.objects.BillingAddress;
import org.selenium.objects.Product;
import org.selenium.objects.User;
import org.selenium.utils.JsonFile;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckoutTest extends BaseTest {
    private Product product;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUp() throws IOException {
        product = new Product(1215);

        checkoutPage = new CheckoutPage(getDriver())
                .load();
    }

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JsonFile.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Cookies cookies = addProductToCart(product, 1);
        injectCookiesToBrowser(cookies);

        checkoutPage
                .load()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransfer()
                .placeOrder();

        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JsonFile.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Cookies signUpCookies = registerNewUserAndGetCookies();
        Cookies cartCookies = addProductToCart(product, 1, signUpCookies);
        injectCookiesToBrowser(cartCookies);

        checkoutPage
                .load()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransfer()
                .placeOrder();

        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");
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
