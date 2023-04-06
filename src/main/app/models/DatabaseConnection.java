package main.app.models;

import com.mysql.cj.jdbc.MysqlDataSource;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    public static Connection getConnection;
    private String url = null;
    private String dbName = null;
    private String userName = null;
    private String password = null;

    public DatabaseConnection() throws IOException {
        // Loads secrets from the properties file
        Properties secrets = new Properties();
        InputStream input = new FileInputStream("secrets.properties");
        secrets.load(input);

        this.url = secrets.getProperty("database.url");
        this.dbName = secrets.getProperty("database.name");
        this.userName = secrets.getProperty("database.admin.username");
        this.password = secrets.getProperty("database.admin.password");
    }

    private DataSource getDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(this.url);
        dataSource.setPassword(this.password);
        dataSource.setUser(this.userName);
        dataSource.setDatabaseName(this.dbName);
        return dataSource;
    }

    public Connection getConnection() throws SQLException {
        DataSource dataSource = getDataSource();
        return dataSource.getConnection();
    }
}

