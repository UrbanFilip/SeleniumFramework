package org.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.selenium.base.BasePage;
import org.selenium.pages.components.Login;
import org.selenium.constants.Endpoint;

public class AccountPage extends BasePage {
    private Login login;
    @FindBy(xpath = "//div[@class='woocommerce-MyAccount-content']/p[1]")
    private WebElement welcomeText;

    @FindBy(css = "[role='alert']")
    private WebElement alert;

    public AccountPage(WebDriver driver) {
        super(driver);
        this.login = new Login(driver);
    }

    public Login getLogin() {
        return login;
    }

    public AccountPage load() {
        load(Endpoint.ACCOUNT.getEndpoint());
        return this;
    }

    public String getWelcomeText() {
        return waitForElementToBeVisible(welcomeText).getText();
    }

    public String getAlert() {
        return waitForElementToBeVisible(alert).getText();
    }

}
