package org.selenium.dataproviders;

import org.selenium.objects.Product;
import org.selenium.utils.JsonFile;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "getFeaturedProducts", parallel = true)
    public static Object[][] getFeaturedProducts() throws IOException {
        Product[] arrayOfProducts = JsonFile.deserializeJson("products.json", Product[].class);

        Object[][] data = new Object[arrayOfProducts.length][1];

        for (int i = 0; i < arrayOfProducts.length; i++) {

            data[i][0] = arrayOfProducts[i].getName();
        }

        return data;
    }
}
