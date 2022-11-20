package org.selenium.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.selenium.constants.Endpoint;
import org.selenium.api.ApiRequests;

import java.util.HashMap;
import java.util.Map;


public class CartApi {
    private Cookies cookies;

    public CartApi() {
    }

    public CartApi(Cookies cookies) {
        this.cookies = cookies;
    }

    public Cookies getCookies() {
        return cookies;
    }

    public Response addToCart(int productId, int quantity) {
        Header header = new Header("content-type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        Map<String, Object> formParams = new HashMap<>();
        formParams.put("product_sku", "");
        formParams.put("product_id", productId);
        formParams.put("quantity", quantity);


        if (cookies == null) {
            cookies = new Cookies();
        }
        Response response = ApiRequests.post(Endpoint.ADD_DO_CART.getEndpoint(), headers, formParams, cookies);

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to add product " + productId + " to the cart, HTTP Status Code: " + response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }
}
