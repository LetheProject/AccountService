package org.amoseman.accountservice.dao.exception;

public class UserDoesNotHaveRoleException extends Exception {
    public UserDoesNotHaveRoleException(String username, String serviceID, String role) {
        super(String.format("User %s does not have role %s in service %s", username, serviceID, role));
    }
}
