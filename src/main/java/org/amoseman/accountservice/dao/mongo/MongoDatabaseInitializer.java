package org.amoseman.accountservice.dao.mongo;

import com.mongodb.client.MongoDatabase;
import org.amoseman.accountservice.dao.DatabaseConnection;
import org.amoseman.accountservice.dao.DatabaseInitializer;

public class MongoDatabaseInitializer implements DatabaseInitializer<MongoDatabase> {
    @Override
    public void init(DatabaseConnection<MongoDatabase> connection) {
        MongoDatabase database = connection.get();
        database.createCollection("accounts");
        database.createCollection("services");
        database.createCollection("requests");
    }
}
