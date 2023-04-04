package kz.bitlab.springmvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum Application {
    INSTANCE;

    private Connection connection;

    public Connection connection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/spring_mvc_db",
                        "postgres",
                        "123qwe123");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return connection;
    }
}
