package org.amoseman.accountservice.dao.exception;

public class UsernameDoesNotExistException extends Exception {
    public UsernameDoesNotExistException(String username) {
        super(String.format("Username %s does not exist", username));
    }
}
