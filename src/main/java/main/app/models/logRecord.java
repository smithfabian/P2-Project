package main.app.models;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// this class will write to log table in database

/**
* I made this method to test the database connection
* if it works it should print out the database date and time
* */
public class logRecord {
    public static void main(String[] args) throws IOException, SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String query = "SELECT NOW() as now";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String now = rs.getString("now");
                System.out.println(now);
            }
        }
        finally {
            conn.close();
        }
    }
}
