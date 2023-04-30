package main.app.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class LogModel {
    private ObservableList<LogRow> table;

    public class LogRow {
        private String logDate;
        private String className;
        private String logLevel;
        private String logMessage;
        public LogRow(String logDate, String className, String logLevel, String logMessage) {
            this.logDate = logDate;
            this.className = className;
            this.logLevel = logLevel;
            this.logMessage = logMessage;
        }
        public String getLogDate() {
            return logDate;
        }

        public String getClassName() {
            return className;
        }

        public String getLogLevel() {
            return logLevel;
        }

        public String getLogMessage() {
            return logMessage;
        }
    }
    public void fillTable() {
        table = FXCollections.observableArrayList();
        try {
            String query =
                    "SELECT * from p2.logs";
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    table.add(new LogRow(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("LogDate")),rs.getString("ClassName"),rs.getString("LogLevel"),rs.getString("LogMessage")));
                }
            }
            finally {
                conn.close();
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<LogRow> getTable() {
        return table;
    }
}
