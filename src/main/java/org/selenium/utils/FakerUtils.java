package org.selenium.utils;

import com.github.javafaker.Faker;

public class FakerUtils {
    private static final Faker faker = new Faker();
    private static Long generateRandomNumber() {

        return faker.number().randomNumber(10, true);
    }

    public static String generateUsername() {

        return faker.name().firstName() + faker.name().lastName() + generateRandomNumber();
    }
}
