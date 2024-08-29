package org.amoseman.accountservice.service;

import com.google.common.collect.ImmutableList;
import org.amoseman.accountservice.dao.RoleDAO;
import org.amoseman.accountservice.dao.ServiceDAO;
import org.amoseman.accountservice.dao.exception.RoleDoesNotExistException;
import org.amoseman.accountservice.dao.exception.ServiceDoesNotExistException;
import org.amoseman.accountservice.dao.exception.UserDoesNotExistException;
import org.amoseman.accountservice.pojo.ServiceDetails;

public class RoleService {
    private final RoleDAO roleDAO;
    private final ServiceDAO serviceDAO;

    public RoleService(RoleDAO roleDAO, ServiceDAO serviceDAO) {
        this.roleDAO = roleDAO;
        this.serviceDAO = serviceDAO;
    }

    public void addRole(String username, String serviceID, String role) throws UserDoesNotExistException, ServiceDoesNotExistException, RoleDoesNotExistException {
        ServiceDetails serviceDetails = serviceDAO.details(serviceID);
        if (!serviceDetails.containsRole(role)) {
            throw new RoleDoesNotExistException(serviceID, role);
        }
        roleDAO.addRole(username, serviceID, role);
    }

    public void removeRole(String username, String serviceID, String role) throws UserDoesNotExistException, ServiceDoesNotExistException {
        if (!serviceDAO.isRegistered(serviceID)) {
            throw new ServiceDoesNotExistException(serviceID);
        }
        roleDAO.removeRole(username, serviceID, role);
    }

    public ImmutableList<String> getRoles(String username, String serviceID) throws UserDoesNotExistException, ServiceDoesNotExistException {
        if (!serviceDAO.isRegistered(serviceID)) {
            throw new ServiceDoesNotExistException(serviceID);
        }
        return roleDAO.getRoles(username, serviceID);
    }
}
