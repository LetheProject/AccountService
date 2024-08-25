package org.amoseman.accountservice.pojo;

import java.util.List;

public class Role {
    private final String name;
    private final List<String> permissions;

    public Role(String name, List<String> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public List<String> getPermissions() {
        return permissions;
    }
}
