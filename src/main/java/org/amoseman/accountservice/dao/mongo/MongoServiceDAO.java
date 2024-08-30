package org.amoseman.accountservice.dao.mongo;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.amoseman.accountservice.dao.DatabaseConnection;
import org.amoseman.accountservice.dao.ServiceDAO;
import org.amoseman.accountservice.dao.exception.ServiceDoesNotExistException;
import org.amoseman.accountservice.dao.exception.ServiceIsAlreadyRegisteredException;
import org.amoseman.accountservice.pojo.Role;
import org.amoseman.accountservice.pojo.ServiceDetails;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class MongoServiceDAO implements ServiceDAO {
    private final DatabaseConnection<MongoDatabase> connection;

    public MongoServiceDAO(DatabaseConnection<MongoDatabase> connection) {
        this.connection = connection;
    }

    @Override
    public boolean isRegistered(String id) {
        return null != connection.get().getCollection("services").find(eq("id", id)).first();
    }

    @Override
    public void register(String id) throws ServiceIsAlreadyRegisteredException {
        Document document = connection.get()
                .getCollection("services")
                .find(eq("id", id))
                .first();
        if (null != document) {
            throw new ServiceIsAlreadyRegisteredException(id);
        }
        connection.get()
                .getCollection("services")
                .insertOne(
                        new Document()
                                .append("id", id)
                                .append("permissions", new ArrayList<String>())
                                .append("roles", new Document())
                );
    }

    @Override
    public void delete(String id) throws ServiceDoesNotExistException {
        long result = connection.get()
                .getCollection("services")
                .deleteOne(eq("id", id))
                .getDeletedCount();
        if (0 == result) {
            throw new ServiceDoesNotExistException(id);
        }
    }

    @Override
    public void update(ServiceDetails details) throws ServiceDoesNotExistException {
        Document service = connection.get()
                .getCollection("services")
                .find(eq("id", details.getID()))
                .first();
        if (null == service) {
            throw new ServiceDoesNotExistException(details.getID());
        }
        Document roles = service.get("roles", Document.class);
        roles.clear();
        for (Role role : details.getRoles()) {
            roles.append(role.getName(), role.getPermissions());
        }

        long result = connection.get()
                .getCollection("services")
                .updateOne(
                        new Document().append("id", details.getID()),
                        Updates.combine(
                                Updates.set("roles", roles),
                                Updates.set("permissions", details.getPermissions())
                        ),
                        new UpdateOptions().upsert(false)
                )
                .getModifiedCount();
        if (0 == result) {
            throw new ServiceDoesNotExistException(details.getID());
        }
    }

    @Override
    public ServiceDetails details(String id) throws ServiceDoesNotExistException {
        Document document = connection.get()
                .getCollection("services")
                .find(eq("id", id))
                .first();
        if (null == document) {
            throw new ServiceDoesNotExistException(id);
        }
        return new ServiceDetails(document);
    }
}
