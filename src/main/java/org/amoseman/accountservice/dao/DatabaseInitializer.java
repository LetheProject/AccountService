package org.amoseman.accountservice.dao;

public interface DatabaseInitializer<T> {
    void init(DatabaseConnection<T> connection);
}
