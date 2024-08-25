package org.amoseman.accountservice.dao.exception;

public class ServiceIsAlreadyRegisteredException extends Exception {
    public ServiceIsAlreadyRegisteredException(String id) {
        super(String.format("Service %s is already registered", id));
    }
}
