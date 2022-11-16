package org.selenium.tests;

import org.selenium.base.BaseTest;
import org.selenium.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {
    private StorePage storePage;

    @BeforeMethod
    public void setUp() {
        storePage = new StorePage(getDriver())
                .load();
    }

    @Test
    public void shouldBeAbleToSearchProductWithPartialMatch() {
        String searchFor = "Blue";
        storePage.search(searchFor);

        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”", "Searched keyword was not searched for. Expected title: " + searchFor + ". Actual product: " + storePage.getTitle());
        Assert.assertTrue(storePage.isProductWithSearchedKeywordVisible(searchFor), "There was a product without searched keyword");
    }

    @Test
    public void shouldNotBeAbleToFindNonExistentProduct() {
        String searchFor = "guitar";
        storePage.search(searchFor);

        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");
        Assert.assertEquals(storePage.getInfo(), "No products were found matching your selection.", "Searched keyword was not searched for. Expected title: " + searchFor + ". Actual product: " + storePage.getTitle());

    }
}
