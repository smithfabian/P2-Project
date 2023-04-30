package main.app.models;

import java.sql.Connection;
import java.sql.SQLException;

public class LogConnectionFactory {
    public Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection();
    }

}
