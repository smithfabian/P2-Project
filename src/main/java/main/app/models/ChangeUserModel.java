package main.app.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangeUserModel {

    private int Id;
    private String usernameTextfield;
    private String passwordTextfield;

    private String UserID_textfield;
    private static final Logger logger = LogManager.getLogger(ChangeUserModel.class.getName());

    private Boolean isAdmin = false;

    // Connect to database to write a new user to it
    public void getUserIntoTable() {

        try (Connection conn = DatabaseConnection.getConnection()) {
            String checkQuery = "SELECT COUNT(*) FROM p2.users WHERE User = ?";
            String insertQuery = "INSERT INTO p2.users (User, Password,IsAdmin) values (?,?,?)";

            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setString(1, usernameTextfield);
                try (ResultSet resultSet = checkStmt.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) == 0) {
                        try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                            insertStmt.setString(1,usernameTextfield);
                            insertStmt.setString(2,passwordTextfield);
                            insertStmt.setBoolean(3,isAdmin);
                            insertStmt.executeUpdate();
                            logger.warn("User " + Session.getLoggedInUser() + ": User with username " + usernameTextfield + "changed" );

                        }

                    }
                }
            }

        } catch (SQLException e) {
            logger.warn("User " + Session.getLoggedInUser() + ": could not change user with username " + usernameTextfield);
            e.printStackTrace();
        }
    }


    // setter methods
    public int getId() { return Id;}

    public void setIsAdmin(boolean isAdmin) { this.isAdmin = isAdmin;}

    public void setUsername_textfield(String usernameTextfield) {this.usernameTextfield = usernameTextfield;
    }

    public void setPassword_textfield(String passwordTextfield) {this.passwordTextfield = passwordTextfield;}

}


