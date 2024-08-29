package org.amoseman.accountservice.dao;

import org.amoseman.accountservice.dao.exception.ServiceDoesNotExistException;
import org.amoseman.accountservice.dao.exception.ServiceIsAlreadyRegisteredException;
import org.amoseman.accountservice.pojo.ServiceDetails;

/**
 * Represents the interface of a service data access object.
 */
public interface ServiceDAO {
    /**
     * Determine if a service is registered.
     * @param id the ID of the service.
     * @return true if it is registered, false otherwise.
     */
    boolean isRegistered(String id);

    /**
     * Register a service.
     * @param id the ID of the service.
     * @throws ServiceIsAlreadyRegisteredException if the service is already registered.
     */
    void register(String id) throws ServiceIsAlreadyRegisteredException;

    /**
     * Unregister a service.
     * @param id the ID of the service.
     * @throws ServiceDoesNotExistException if the service is not registered.
     */
    void delete(String id) throws ServiceDoesNotExistException;

    /**
     * Update the details of a service.
     * @param details the details of the service.
     * @throws ServiceDoesNotExistException if the service is not registered.
     */
    void update(ServiceDetails details) throws ServiceDoesNotExistException;

    /**
     * Retrieve the details of a service.
     * @param id the ID of the service.
     * @return the details of the service.
     * @throws ServiceDoesNotExistException if the service is not registered.
     */
    ServiceDetails details(String id) throws ServiceDoesNotExistException;

}
