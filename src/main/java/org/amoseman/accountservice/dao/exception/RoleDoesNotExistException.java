package org.amoseman.accountservice.dao.exception;

public class RoleDoesNotExistException extends Exception {
    public RoleDoesNotExistException(String serviceID, String role) {
        super(String.format("Role %s does not exist in service %s", role, serviceID));
    }
}
