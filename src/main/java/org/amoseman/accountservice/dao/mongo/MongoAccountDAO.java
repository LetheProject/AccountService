package org.amoseman.accountservice.dao.mongo;

import com.google.common.collect.ImmutableList;
import com.mongodb.client.MongoDatabase;
import org.amoseman.accountservice.dao.AccountDAO;
import org.amoseman.accountservice.dao.DatabaseConnection;
import org.amoseman.accountservice.dao.exception.UserDoesNotExistException;
import org.amoseman.accountservice.dao.exception.UsernameAlreadyInUseException;
import org.amoseman.accountservice.pojo.AccountCreationRequest;
import org.amoseman.accountservice.pojo.AccountDetails;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class MongoAccountDAO implements AccountDAO {
    private final DatabaseConnection<MongoDatabase> connection;

    public MongoAccountDAO(DatabaseConnection<MongoDatabase> connection) {
        this.connection = connection;
    }


    @Override
    public void request(AccountCreationRequest request) throws UsernameAlreadyInUseException {
        connection.get()
                .getCollection("requests")
                .insertOne(new Document()
                        .append("username", request.getUsername())
                        .append("password", request.getPassword())
                );
    }

    @Override
    public void accept(String username) throws UserDoesNotExistException {
        Document document = connection.get()
                .getCollection("requests")
                .findOneAndDelete(eq("username", username));
        if (null == document) {
            throw new UserDoesNotExistException(username);
        }
        connection.get()
                .getCollection("accounts")
                .insertOne(
                        new Document()
                                .append("username", document.get("username"))
                                .append("password", document.get("password"))
                                .append("display-name", document.get("username"))
                                .append("pronouns", "n/a")
                                .append("description", "This user has yet to write a description.")
                );
    }

    @Override
    public void reject(String username) throws UserDoesNotExistException {
        long result = connection.get()
                .getCollection("requests")
                .deleteOne(
                        eq("username", username)
                )
                .getDeletedCount();
        if (0 == result) {
            throw new UserDoesNotExistException(username);
        }
    }

    @Override
    public ImmutableList<String> listRequests() {
        return null;
    }

    @Override
    public void delete(String username) throws UserDoesNotExistException {

    }

    @Override
    public void update(AccountDetails details) throws UserDoesNotExistException {

    }

    @Override
    public AccountDetails details(String username) throws UserDoesNotExistException {
        return null;
    }

    @Override
    public String password(String username) throws UserDoesNotExistException {
        return null;
    }
}
