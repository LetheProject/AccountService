package org.amoseman.accountservice.dao.exception;

public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException(String username) {
        super(String.format("Username %s does not exist", username));
    }
}
