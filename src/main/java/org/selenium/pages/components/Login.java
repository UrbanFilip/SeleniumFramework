package org.selenium.pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.base.BasePage;
import org.selenium.objects.User;
import org.selenium.pages.AccountPage;

public class Login extends BasePage {
    @FindBy(id = "username")
    private WebElement usernameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(name = "login")
    private WebElement loginButton;

    @FindBy(css = ".woocommerce-form")
    private WebElement form;

    public Login(WebDriver driver) {
        super(driver);
    }


    public Login enterUserName(String username) {
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBeNotEmpty(form, "style")));
        System.out.println(form.getAttribute("style"));
        usernameField.clear();
        usernameField.sendKeys(username);

        return this;
    }

    public Login enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);

        return this;
    }

    public Login clickLoginButton() {
        waitForElementToBeClickable(loginButton).click();
        return this;
    }


    public AccountPage login(String username, String password) {
        enterUserName(username)
                .enterPassword(password)
                .clickLoginButton();

        return new AccountPage(driver);
    }

    public AccountPage login(User user) {
        enterUserName(user.getUsername())
                .enterPassword(user.getPassword())
                .clickLoginButton();

        return new AccountPage(driver);
    }
}
