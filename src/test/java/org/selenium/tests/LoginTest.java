package org.selenium.tests;

import org.selenium.pages.AccountPage;
import org.selenium.base.BaseTest;
import org.selenium.objects.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {
    private AccountPage accountPage;

    @BeforeMethod
    public void setUp() {
        accountPage = new AccountPage(getDriver())
                .load();
    }

    @Test
    public void shouldBeAbleToLoginWithExistingAccount() {
        User user = registerNewUser();
        accountPage.getLogin().login(user);

        Assert.assertTrue(accountPage.getWelcomeText().contains("Hello " + user.getUsername()),
                "The text is not visible. Expected text: " + "Hello " + user.getUsername() + "Actual text: " + accountPage.getWelcomeText());
    }

    @Test
    public void shouldNotBeAbleToLoginWithIncorrectData() {
        User user = new User("notExisting", "wrongPassword");
        accountPage.getLogin().login(user);


        Assert.assertEquals(accountPage.getAlert(), "Error: The username " + user.getUsername() + " is not registered on this site. If you are unsure of your username, try your email address instead.",
                "The text is not visible. Actual text: " + accountPage.getAlert());
    }

    @Test
    public void shouldNotBeAbleToLoginWithIncorrectPassword() {
        User user = registerNewUser();
        accountPage.getLogin().login(user.getUsername(), "wrongPassword");

        Assert.assertEquals(accountPage.getAlert(), "Error: The password you entered for the username " + user.getUsername() + " is incorrect. Lost your password?",
                "The text is not visible. Actual text: " + accountPage.getAlert());
    }

    @Test
    public void shouldNotBeAbleToLoginWithBlankPassword() {
        User user = registerNewUser();

        accountPage.getLogin().login(user.getUsername(), "");

        Assert.assertEquals(accountPage.getAlert(), "Error: The password field is empty.",
                "The text is not visible. Actual text: " + accountPage.getAlert());
    }
}
