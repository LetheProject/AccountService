package org.amoseman.accountservice.pojo;

import org.bson.Document;

/**
 * Encapsulates the details of an account.
 */
public class AccountDetails {
    private final String username;
    private final String displayName;
    private final String pronouns;
    private final String description;

    /**
     * Instantiate an account details object.
     * @param username the username.
     * @param displayName the display name.
     * @param pronouns the pronouns.
     * @param description the description.
     */
    public AccountDetails(String username, String displayName, String pronouns, String description) {
        this.username = username;
        this.displayName = displayName;
        this.pronouns = pronouns;
        this.description = description;
    }

    /**
     * Instantiate an account details object.
     * @param document the document to extract the username, display name, pronouns, and description from.
     */
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
