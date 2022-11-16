package org.selenium.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.selenium.constants.Endpoint;
import org.selenium.api.ApiRequests;
import org.selenium.objects.User;

import java.util.HashMap;
import java.util.Map;

public class SignUpApi {
    private Cookies cookies;

    public Cookies getCookies() {
        return cookies;
    }

    private String fetchRegisterNonceValue() {
        Response response = getAccount();
        Document doc = Jsoup.parse(response.getBody().prettyPrint());
        Element element = doc.selectFirst("#woocommerce-register-nonce");
        if (element != null) {
            return element.attr("value");
        } else {
            throw new IllegalStateException("Element was not found");
        }
    }

    private Response getAccount() {
        Cookies cookies = new Cookies();
        Response response = ApiRequests.get(Endpoint.ACCOUNT.getEndpoint(), cookies);

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch the account, HTTP Status Code: " + response.getStatusCode());
        }
        return response;
    }

    public Response register(User user) {
        Cookies cookies = new Cookies();
        Header header = new Header("content-type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        Map<String, Object> formParams = new HashMap<>();
        formParams.put("username", user.getUsername());
        formParams.put("email", user.getEmail());
        formParams.put("password", user.getPassword());
        formParams.put("woocommerce-register-nonce", fetchRegisterNonceValue());
        formParams.put("register", "register");

        Response response = ApiRequests.post(Endpoint.ACCOUNT.getEndpoint(), headers, formParams, cookies);

        if (response.getStatusCode() != 302) {
            throw new RuntimeException("Failed to register the account, HTTP Status Code: " + response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }
}
