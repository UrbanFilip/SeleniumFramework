package org.selenium.constants;

public enum Endpoint {
    ADD_DO_CART("/?wc-ajax=add_to_cart"),
    ACCOUNT("/account");

    private String endpoint;
    Endpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
