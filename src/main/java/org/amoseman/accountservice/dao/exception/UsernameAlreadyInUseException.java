package org.amoseman.accountservice.dao.exception;

public class UsernameAlreadyInUseException extends Exception {
    public UsernameAlreadyInUseException(String username) {
        super(String.format("Username %s is already in use", username));
    }
}
