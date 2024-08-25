package org.amoseman.accountservice.dao;

import com.google.common.collect.ImmutableList;
import org.amoseman.accountservice.dao.exception.UsernameAlreadyInUseException;
import org.amoseman.accountservice.dao.exception.UserDoesNotExistException;
import org.amoseman.accountservice.pojo.AccountCreationRequest;
import org.amoseman.accountservice.pojo.AccountDetails;

/**
 * Represents the interface of an account data access object.
 */
public interface AccountDAO {
    /**
     * Request the creation of a new account.
     * @param request the request.
     * @throws UsernameAlreadyInUseException if the username is already in use.
     */
    void request(AccountCreationRequest request) throws UsernameAlreadyInUseException;

    /**
     * Accept an account creation request.
     * @param username the username of the account.
     * @throws UserDoesNotExistException if no request with the corresponding username exists.
     */
    void accept(String username) throws UserDoesNotExistException;

    /**
     * Reject an account creation request.
     * @param username the username of the account.
     * @throws UserDoesNotExistException if no request with the corresponding username exists.
     */
    void reject(String username) throws UserDoesNotExistException;

    /**
     * List the pending account creation requests.
     * @return the pending account creation requests.
     */
    ImmutableList<String> listRequests();

    /**
     * Delete an account.
     * @param username the username of the account.
     * @throws UserDoesNotExistException if the account does not exist.
     */
    void delete(String username) throws UserDoesNotExistException;

    /**
     * Update the account details.
     * @param details the updated details.
     * @throws UserDoesNotExistException if the account does not exist.
     */
    void update(AccountDetails details) throws UserDoesNotExistException;

    /**
     * Retrieve the account details.
     * @param username the username of the account.
     * @return the details of the account.
     * @throws UserDoesNotExistException if the account does not exist.
     */
    AccountDetails details(String username) throws UserDoesNotExistException;

    /**
     * Retrieve the password hash of an account.
     * @param username the username of the account.
     * @return the password hash in base 64.
     * @throws UserDoesNotExistException if the account does not exist.
     */
    String password(String username) throws UserDoesNotExistException;
}
