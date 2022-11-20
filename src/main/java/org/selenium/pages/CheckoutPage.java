package org.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.selenium.base.BasePage;
import org.selenium.pages.components.Login;
import org.selenium.objects.BillingAddress;

import java.util.List;


public class CheckoutPage extends BasePage {
    private Login login;

    @FindBy(id = "billing_first_name")
    private WebElement firstNameField;
    @FindBy(id = "billing_last_name")
    private WebElement lastNameField;
    @FindBy(id = "billing_address_1")
    private WebElement addressLineOneField;
    @FindBy(id = "billing_city")
    private WebElement billingCityField;
    @FindBy(id = "billing_postcode")
    private WebElement billingPostCodeField;
    @FindBy(id = "billing_email")
    private WebElement billingEmailField;
    @FindBy(id = "place_order")
    private WebElement placeOrderButton;
    @FindBy(css = ".woocommerce-notice")
    private WebElement successNotice;
    @FindBy(className = "showlogin")
    private WebElement clickHereToLoginLink;
    @FindBy(id = "billing_country")
    private WebElement countryDropdown;
    @FindBy(id = "select2-billing_country-container")
    private WebElement alternateCountryDropdown;
    @FindBy(id = "billing_state")
    private WebElement stateDropdown;
    @FindBy(id = "select2-billing_state-container")
    private WebElement alternateStateDropdown;
    @FindBy(css = ".blockOverlay")
    private List<WebElement> overlay;
    @FindBy(id = "payment_method_bacs")
    private WebElement directBankTransferRadioButton;
    @FindBy(css = "td[class='product-name']")
    private WebElement productName;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.login = new Login(driver);
    }

    public Login getLogin() {
        return login;
    }

    public CheckoutPage load() {
        load("/checkout/");
        return this;
    }

    public CheckoutPage enterFirstName(String firstName) {
        waitForElementToBeVisible(firstNameField);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        WebElement e = waitForElementToBeVisible(lastNameField);
        e.clear();
        e.sendKeys(lastName);

        return this;
    }

    public CheckoutPage enterAddressLineOne(String address) {
        WebElement e = waitForElementToBeVisible(addressLineOneField);
        e.clear();
        e.sendKeys(address);

        return this;
    }

    public CheckoutPage enterCity(String city) {
        WebElement e = waitForElementToBeVisible(billingCityField);
        e.clear();
        e.sendKeys(city);

        return this;
    }

    public CheckoutPage enterPostCode(String postCode) {
        WebElement e = waitForElementToBeVisible(billingPostCodeField);
        e.clear();
        e.sendKeys(postCode);

        return this;
    }

    public CheckoutPage enterEmail(String email) {
        WebElement e = waitForElementToBeVisible(billingEmailField);
        e.clear();
        e.sendKeys(email);

        return this;
    }

    public CheckoutPage selectCountry(String country) {
        waitForElementToBeClickable(alternateCountryDropdown).click();
        WebElement e = waitForElementToBeClickable(driver.findElement(By.xpath("//li[text()='" + country + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();

        return this;
    }

    public CheckoutPage selectState(String state) {
        waitForElementToBeClickable(alternateStateDropdown).click();
        WebElement e = waitForElementToBeClickable(driver.findElement(By.xpath("//li[text()='" + state + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress) {
        enterFirstName(billingAddress.getFirstName())
                .enterLastName(billingAddress.getLastName())
                .selectCountry(billingAddress.getCountry())
                .enterAddressLineOne(billingAddress.getAddressLineOne())
                .enterCity(billingAddress.getCity())
                .selectState(billingAddress.getState())
                .enterPostCode(billingAddress.getPostalCode())
                .enterEmail(billingAddress.getEmail());

        return this;
    }

    public CheckoutPage placeOrder() {
        waitForOverlay(overlay);
        waitForElementToBeClickable(placeOrderButton).click();

        return this;
    }

    public String getNotice() {
        return waitForElementToBeVisible(successNotice).getText();
    }

    public CheckoutPage clickHereToLoginLink() {
        clickHereToLoginLink.click();

        return this;
    }

    public CheckoutPage selectDirectBankTransfer() {
        WebElement e = waitForElementToBeClickable(directBankTransferRadioButton);
        if (!e.isSelected()) {
            e.click();
        }

        return this;
    }

    public String getProductName() {
        return waitForElementToBeVisible(productName).getText();
    }
}
