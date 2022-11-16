package org.selenium.api;

import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiRequests extends SpecificationBuilder {
    public static Response post(String endpoint, Headers headers, Map<String, Object> formParams, Cookies cookies) {
        return RestAssured.given(getRequestSpec()).
                headers(headers).
                formParams(formParams).
                cookies(cookies).
        when().
                post(endpoint).
        then()
                .spec(getResponseSpec()).
                extract().
                response();

    }

    public static Response get(String endpoint, Cookies cookies) {
        return RestAssured.given(getRequestSpec()).
                cookies(cookies).
        when().
                get(endpoint).
        then().
                spec(getResponseSpec()).
                extract().
                response();
    }

    public static List<Cookie> convertRestAssuredCookiesToSeleniumCookies(Cookies cookies) {
        List<org.openqa.selenium.Cookie> seleniumCookies = new ArrayList<>();
        for (io.restassured.http.Cookie cookie : cookies) {
            seleniumCookies.add(
                    new org.openqa.selenium.Cookie(
                            cookie.getName(), cookie.getValue(), cookie.getDomain(), cookie.getPath(),
                            cookie.getExpiryDate(), cookie.isSecured(), cookie.isHttpOnly(), cookie.getSameSite()));
        } return seleniumCookies;
    }
}
