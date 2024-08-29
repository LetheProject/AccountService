package org.amoseman.accountservice.dao;

public interface DatabaseConnection<T> {
    DatabaseConnection<T> connect(String url);
    T get();
}
