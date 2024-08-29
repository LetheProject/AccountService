package org.amoseman.accountservice.pojo;

import org.bson.Document;

public class AccountDetails {
    private final String username;
    private final String displayName;
    private final String pronouns;
    private final String description;

    public AccountDetails(String username, String displayName, String pronouns, String description) {
        this.username = username;
        this.displayName = displayName;
        this.pronouns = pronouns;
        this.description = description;
    }

    public AccountDetails(Document document) {
        this.username = document.getString("username");
        this.displayName = document.getString("display-name");
        this.pronouns = document.getString("pronouns");
        this.description = document.getString("description");
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
