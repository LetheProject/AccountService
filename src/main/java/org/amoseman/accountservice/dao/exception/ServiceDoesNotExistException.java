package org.amoseman.accountservice.dao.exception;

public class ServiceDoesNotExistException extends Exception {
    public ServiceDoesNotExistException(String id) {
        super(String.format("Service %s does not exist", id));
    }
}
