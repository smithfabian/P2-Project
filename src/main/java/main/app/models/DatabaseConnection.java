package main.app.models;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private DatabaseConnection (){
        // makes sure the class can not be initialized from the outside
    }
    private static final BasicDataSource dataSource = new BasicDataSource();
    private static final Properties secrets = loadSecrets();

    // Static initializer block initialize static variables only once when the class first loaded
    static {
        dataSource.setUrl(secrets.getProperty("database.url"));
        dataSource.setPassword(secrets.getProperty("database.admin.password"));
        dataSource.setUsername(secrets.getProperty("database.admin.username"));
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxTotal(20);
        dataSource.setMaxOpenPreparedStatements(100);
    }

    private static Properties loadSecrets(){
        // Loads secrets from the properties file
        Properties secrets = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("secrets.properties");
            secrets.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return secrets;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}

