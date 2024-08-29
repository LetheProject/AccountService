package org.amoseman.accountservice.dao.mongo;

import com.google.common.collect.ImmutableList;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.amoseman.accountservice.dao.DatabaseConnection;
import org.amoseman.accountservice.dao.RoleDAO;
import org.amoseman.accountservice.dao.exception.RoleDoesNotExistException;
import org.amoseman.accountservice.dao.exception.ServiceDoesNotExistException;
import org.amoseman.accountservice.dao.exception.UserDoesNotExistException;
import org.amoseman.accountservice.dao.exception.UserDoesNotHaveRoleException;
import org.amoseman.accountservice.pojo.AccountRoles;
import org.amoseman.accountservice.pojo.Roles;
import org.bson.Document;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MongoRoleDAO implements RoleDAO {
    private final DatabaseConnection<MongoDatabase> connection;

    public MongoRoleDAO(DatabaseConnection<MongoDatabase> connection) {
        this.connection = connection;
    }

    private AccountRoles roles(String username) throws UserDoesNotExistException {
        Document document = connection.get()
                .getCollection("accounts")
                .find(eq("username", username))
                .first();
        if (null == document) {
            throw new UserDoesNotExistException(username);
        }
        return document.get("roles", AccountRoles.class);
    }

    @Override
    public void addRole(String username, String serviceID, String role) throws UserDoesNotExistException {
        AccountRoles accountRoles = roles(username);
        Roles roles = accountRoles.getRoles().get(serviceID);
        roles = roles.add(role);
        accountRoles.getRoles().put(serviceID, roles);
        long result = connection.get()
                .getCollection("accounts")
                .updateOne(
                        new Document().append("username", username),
                        Updates.combine(
                                Updates.set("roles", accountRoles)
                        ),
                        new UpdateOptions().upsert(false)
                ).getModifiedCount();
        if (0 == result) {
            throw new UserDoesNotExistException(username);
        }
    }

    @Override
    public void removeRole(String username, String serviceID, String role) throws UserDoesNotExistException {
        AccountRoles accountRoles = roles(username);
        Roles roles = accountRoles.getRoles().get(serviceID);
        roles = roles.remove(role);
        accountRoles.getRoles().put(serviceID, roles);
        long result = connection.get()
                .getCollection("accounts")
                .updateOne(
                        new Document().append("username", username),
                        Updates.combine(
                                Updates.set("roles", accountRoles)
                        ),
                        new UpdateOptions().upsert(false)
                ).getModifiedCount();
        if (0 == result) {
            throw new UserDoesNotExistException(username);
        }
    }

    @Override
    public ImmutableList<String> getRoles(String username, String serviceID) throws UserDoesNotExistException {
        Roles roles = roles(username).getRoles().get(serviceID);
        if (null == roles) {
            return ImmutableList.of();
        }
        return ImmutableList.copyOf(roles.getRoles());
    }
}
