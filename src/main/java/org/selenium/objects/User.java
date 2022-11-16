package org.selenium.objects;

import org.selenium.utils.FakerUtils;

public class User {
    private String username;
    private String password;
    private String email;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
        this.username = FakerUtils.generateUsername();
        this.password = "test123";
        this.email = username + "@email.com";
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }
}
