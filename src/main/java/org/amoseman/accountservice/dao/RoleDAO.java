package org.amoseman.accountservice.dao;

import com.google.common.collect.ImmutableList;
import org.amoseman.accountservice.dao.exception.RoleDoesNotExistException;
import org.amoseman.accountservice.dao.exception.ServiceDoesNotExistException;
import org.amoseman.accountservice.dao.exception.UserDoesNotExistException;
import org.amoseman.accountservice.dao.exception.UserDoesNotHaveRoleException;

/**
 * Represents the interface of a role data access object.
 */
public interface RoleDAO {
    /**
     * Grant a role to a user in the domain of a service.
     * @param username the username of the user.
     * @param serviceID the ID of the service.
     * @param role the role.
     * @throws UserDoesNotExistException if the user does not exist.
     */
    void addRole(String username, String serviceID, String role) throws UserDoesNotExistException;

    /**
     * Remove a role from a user in the domain of a service.
     * @param username the username of the user.
     * @param serviceID the ID of the service.
     * @param role the role.
     * @throws UserDoesNotExistException if the user does not exist.
     */
    void removeRole(String username, String serviceID, String role) throws UserDoesNotExistException;

    /**
     * Retrieve the roles of a user in the domain of a service.
     * @param username the username of the user.
     * @param serviceID the ID of the service.
     * @throws UserDoesNotExistException if the user does not exist.
     */
    ImmutableList<String> getRoles(String username, String serviceID) throws UserDoesNotExistException;
}
