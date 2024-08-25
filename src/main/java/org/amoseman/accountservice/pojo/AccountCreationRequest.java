package org.amoseman.accountservice.pojo;

public class AccountCreationRequest {
    private final String username;
    private final String password;

    public AccountCreationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
