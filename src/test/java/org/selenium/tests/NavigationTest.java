package org.selenium.tests;

import org.selenium.pages.HomePage;
import org.selenium.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test
    public void navigateFromHomeToStoreUsingMainMenu() {
        StorePage storePage = new HomePage(getDriver()).load()
                .getHeader().navigateToStoreUsingMenu();

        Assert.assertEquals(storePage.getTitle(), "Store");
    }
}
