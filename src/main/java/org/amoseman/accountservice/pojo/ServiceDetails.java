package org.amoseman.accountservice.pojo;

import com.google.common.collect.ImmutableList;

public class ServiceDetails {
    private final String id;
    private final ImmutableList<String> permissions;
    private final ImmutableList<Role> roles;

    public ServiceDetails(String id, ImmutableList<String> permissions, ImmutableList<Role> roles) {
        this.id = id;
        this.permissions = permissions;
        this.roles = roles;
    }

    public String getID() {
        return id;
    }

    public ImmutableList<String> getPermissions() {
        return permissions;
    }

    public ImmutableList<Role> getRoles() {
        return roles;
    }
}
