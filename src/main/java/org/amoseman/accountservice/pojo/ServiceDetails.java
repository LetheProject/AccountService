package org.amoseman.accountservice.pojo;

import com.google.common.collect.ImmutableList;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

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
        Document roles = document.get("roles", Document.class);
        List<Role> rolesList = new ArrayList<>();
        roles.forEach((k, v) -> {
            String key = k;
            List<String> value = (List<String>) v;
            rolesList.add(new Role(key, value));
        });
        this.roles = ImmutableList.copyOf(rolesList);
    }

    public boolean containsRole(String roleName) {
        for (Role role : roles) {
            if (role.getName().equals(roleName)) {
                return true;
            }
        }
        return false;
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
