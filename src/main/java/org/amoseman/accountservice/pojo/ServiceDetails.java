package org.amoseman.accountservice.pojo;

import com.google.common.collect.ImmutableList;
import org.bson.Document;

public class ServiceDetails {
    private final String id;
    private final ImmutableList<String> permissions;
    private final ImmutableList<Role> roles;

    public ServiceDetails(String id, ImmutableList<String> permissions, ImmutableList<Role> roles) {
        this.id = id;
        this.permissions = permissions;
        this.roles = roles;
    }

    public ServiceDetails(Document document) {
        this.id = document.getString("id");
        this.permissions = ImmutableList.copyOf(document.getList("permissions", String.class));
        this.roles = ImmutableList.copyOf(document.getList("roles", Role.class));
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
