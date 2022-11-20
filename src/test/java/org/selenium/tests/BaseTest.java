package org.selenium.tests;

import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.api.actions.CartApi;
import org.selenium.api.actions.SignUpApi;
import org.selenium.factory.DriverManagerFactory;
import org.selenium.api.ApiRequests;
import org.selenium.constants.BrowserType;
import org.selenium.objects.Product;
import org.selenium.objects.User;
import org.selenium.utils.PropertyReader;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseTest {
    private String browser;

    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    protected WebDriver getDriver() {
        return this.driver.get();
    }

    @BeforeMethod
    public void startDriver() {
        browser = System.getProperty("browser", PropertyReader.BROWSER.getProperty());
        setDriver(DriverManagerFactory.getManager(BrowserType.valueOf(browser)).createDriver());
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", "
                + "DRIVER = " + getDriver());
    }

    @AfterMethod
    public void quitDriver(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            File destFile = new File("screenshot/" + browser + "/" +
                    result.getTestClass().getRealClass().getSimpleName() + "_" +
                    result.getMethod().getMethodName() + ".png");

            takeScreenshot(destFile);
        }

        getDriver().quit();
    }

    private void takeScreenshot(File destFile) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, destFile);
    }

    public void injectCookiesToBrowser(Cookies cookies) {
        List<Cookie> seleniumCookies = ApiRequests.convertRestAssuredCookiesToSeleniumCookies(cookies);

        for (Cookie cookie : seleniumCookies) {
            getDriver().manage().addCookie(cookie);
        }
    }

    public User registerNewUser() {
        User user = new User();
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);

        return user;
    }

    public Cookies registerNewUserAndGetCookies() {
        User user = new User();
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);

        return signUpApi.getCookies();
    }


    public Cookies addProductToCart(Product product, int quantity)  {
        CartApi cartApi = new CartApi();
        cartApi.addToCart(product.getId(), quantity);

        return cartApi.getCookies();
    }

    public Cookies addProductToCart(Product product, int quantity, Cookies cookies)  {
        CartApi cartApi = new CartApi(cookies);
        cartApi.addToCart(product.getId(), quantity);

        return cartApi.getCookies();
    }
}
