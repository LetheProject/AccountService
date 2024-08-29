package org.amoseman.accountservice.dao.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.amoseman.accountservice.dao.DatabaseConnection;

public class MongoDatabaseConnection implements DatabaseConnection<MongoDatabase> {
    private final MongoDatabase database;

    private MongoDatabaseConnection(MongoDatabase database) {
        this.database = database;
    }

    @Override
    public DatabaseConnection<MongoDatabase> connect(String url) {
        MongoClient client = MongoClients.create(url);
        MongoDatabase database = client.getDatabase("account-service");
        return new MongoDatabaseConnection(database);
    }

    @Override
    public MongoDatabase get() {
        return database;
    }
}
