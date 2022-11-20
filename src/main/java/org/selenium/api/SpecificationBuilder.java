package org.selenium.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.selenium.utils.PropertyReader;

public class SpecificationBuilder {

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder().
                setBaseUri(PropertyReader.BASE_URL.getProperty())
                .log(LogDetail.ALL).build();

    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).build();
    }
}

