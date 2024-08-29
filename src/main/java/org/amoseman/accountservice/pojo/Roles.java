package org.amoseman.accountservice.pojo;

import com.google.common.collect.ImmutableList;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Roles {
    private final String serviceID;
    private final ImmutableList<String> roles;

    public Roles(String serviceID, ImmutableList<String> roles) {
        this.serviceID = serviceID;
        this.roles = roles;
    }

    public Roles add(String role) {
        List<String> roles = new ArrayList<>(this.roles);
        roles.add(role);
        return new Roles(serviceID, ImmutableList.copyOf(roles));
    }

    public Roles remove(String role) {
        List<String> roles = new ArrayList<>(this.roles);
        roles.remove(role);
        return new Roles(serviceID, ImmutableList.copyOf(roles));
    }

    public String getServiceID() {
        return serviceID;
    }

    public List<String> getRoles() {
        return roles;
    }
}
