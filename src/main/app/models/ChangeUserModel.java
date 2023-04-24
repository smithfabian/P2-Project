package main.app.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangeUserModel {

    private int Id;
    private String usernameTextfield;
    private String passwordTextfield;

    private Boolean isAdmin;

    // Connect to database to write a new user to it
    public void getUserIntoTable() {

        try (Connection conn = DatabaseConnection.getConnection()) {
            String checkQuery = "SELECT COUNT(*) FROM p2.users WHERE User = ?";
            String insertQuery = "INSERT INTO p2.users (User, Password) values (?,?)";

            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setString(1, usernameTextfield);
                try (ResultSet resultSet = checkStmt.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) == 0) {
                        try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                            insertStmt.setString(1,usernameTextfield);
                            insertStmt.setString(2,passwordTextfield);
                            insertStmt.executeUpdate();

                        }

                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUserToAdmin() throws SQLException {
        String insertQuery = "INSERT INTO p2.users (IsAdmin) values (?)";

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
        try {

        insertStmt.setBoolean(1, isAdmin == getIsAdmin(true));
        insertStmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // setter methods

    public Boolean getIsAdmin(boolean b) {
        return true;
    }
    public int getId() { return Id;}

    public void setUsername_textfield(String usernameTextfield) {
        this.usernameTextfield = usernameTextfield;
    }

    public void setPassword_textfield(String passwordTextfield) {
        this.passwordTextfield = passwordTextfield;

    }

}


