package org.amoseman.accountservice.pojo;

import java.util.Map;

public class AccountRoles {
    private final Map<String, Roles> roles;

    public AccountRoles(Map<String, Roles> roles) {
        this.roles = roles;
    }

    public Map<String, Roles> getRoles() {
        return roles;
    }
}
