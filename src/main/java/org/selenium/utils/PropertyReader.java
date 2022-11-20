package org.selenium.utils;

import org.selenium.constants.EnvType;

import java.util.Properties;

public enum PropertyReader {
    BASE_URL(),
    BROWSER(),
    HEADLESS(),
    WAIT_TIMEOUT;

    private final Properties properties;
    private String configPath = "./environments/";

    PropertyReader() {
        String env = System.getProperty("env", String.valueOf(EnvType.STAGE));
        switch (EnvType.valueOf(env)) {
            case STAGE -> this.properties = PropertyUtils.propertyLoader(configPath + "stg_config.properties");
            case PRODUCTION -> this.properties = PropertyUtils.propertyLoader(configPath + "prod_config.properties");
            default -> throw new IllegalStateException("Invalid env type: " + env);
        }
    }

    public String getProperty() {

        return properties.getProperty(this.toString());
    }


}

