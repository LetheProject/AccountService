package org.amoseman.accountservice.pojo;

public class Account {
    private final String username;
    private final String displayName;
    private final String pronouns;
    private final String description;

    public Account(String username, String displayName, String pronouns, String description) {
        this.username = username;
        this.displayName = displayName;
        this.pronouns = pronouns;
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPronouns() {
        return pronouns;
    }

    public String getDescription() {
        return description;
    }
}
