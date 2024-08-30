package org.amoseman.accountservice.dao.mongo;

import com.google.common.collect.ImmutableList;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.amoseman.accountservice.dao.DatabaseConnection;
import org.amoseman.accountservice.dao.RoleDAO;
import org.amoseman.accountservice.dao.exception.UserDoesNotExistException;
import org.amoseman.accountservice.dao.exception.UserDoesNotHaveRoleException;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MongoRoleDAO implements RoleDAO {
    private final DatabaseConnection<MongoDatabase> connection;

    public MongoRoleDAO(DatabaseConnection<MongoDatabase> connection) {
        this.connection = connection;
    }

    private Document roles(String username) throws UserDoesNotExistException {
        Document document = connection.get()
                .getCollection("accounts")
                .find(eq("username", username))
                .first();
        if (null == document) {
            throw new UserDoesNotExistException(username);
        }
        return document.get("roles", Document.class);
    }

    @Override
    public void addRole(String username, String serviceID, String role) throws UserDoesNotExistException {
        Document roles = roles(username);
        List<String> rolesList = roles.getList(serviceID, String.class);
        if (null == rolesList) {
            rolesList = new ArrayList<>();
        }
        if (!rolesList.contains(role)) {
            rolesList.add(role);
        }
        roles.put(serviceID, rolesList);
        long result = connection.get()
                .getCollection("accounts")
                .updateOne(
                        new Document().append("username", username),
                        Updates.combine(
                                Updates.set("roles", roles)
                        ),
                        new UpdateOptions().upsert(false)
                ).getModifiedCount();
        if (0 == result) {
            throw new UserDoesNotExistException(username);
        }
    }

    @Override
    public void removeRole(String username, String serviceID, String role) throws UserDoesNotExistException, UserDoesNotHaveRoleException {
        Document roles = roles(username);
        List<String> rolesList = roles.getList(serviceID, String.class);
        if (null == rolesList) {
            throw new UserDoesNotHaveRoleException(username, serviceID, role);
        }
        if (!rolesList.remove(role)) {
            throw new UserDoesNotHaveRoleException(username, serviceID, role);
        }
        roles.put(serviceID, rolesList);
        long result = connection.get()
                .getCollection("accounts")
                .updateOne(
                        new Document().append("username", username),
                        Updates.combine(
                                Updates.set("roles", roles)
                        ),
                        new UpdateOptions().upsert(false)
                ).getModifiedCount();
        if (0 == result) {
            throw new UserDoesNotExistException(username);
        }
    }

    @Override
    public ImmutableList<String> getRoles(String username, String serviceID) throws UserDoesNotExistException {
        List<String> roles = roles(username).getList(serviceID, String.class);
        if (null == roles) {
            return ImmutableList.of();
        }
        return ImmutableList.copyOf(roles);
    }
}
