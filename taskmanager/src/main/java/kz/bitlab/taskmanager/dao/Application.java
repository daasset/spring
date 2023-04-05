package kz.bitlab.taskmanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum Application {
    INSTANCE;

    private Connection connection;

    public Connection connection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/taskmanager",
                        "postgres",
                        "123qwe123");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}