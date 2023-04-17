package main.app.models;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangeUserModel {

    private int Id;
    private TextField usernameTextfield;
    private TextField passwordTextfield;

    // CONNECT TO DATABASE TO WRITE TO IT
    public void GetUserIntoTable() {

        try (Connection conn = DatabaseConnection.getConnection()) {
            String checkQuery = "SELECT COUNT(*) FROM p2.users WHERE Id = ?";
            String insertQuery = "INSERT INTO p2.users (Id,User, Password) values (?,?,?)";

            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, Id);
                try (ResultSet resultSet = checkStmt.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) == 0) {
                        try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                            insertStmt.setInt(1, Id);
                            insertStmt.setString(2, String.valueOf(usernameTextfield));
                            insertStmt.setString(3, String.valueOf(passwordTextfield));
                            insertStmt.executeUpdate();

                        }

                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // setter methods
    public int getId() { return Id;}

    public void setUsername_textfield(TextField usernameTextfield) {
        this.usernameTextfield = usernameTextfield;
    }

    public void setPassword_textfield(TextField passwordTextfield) {
        this.passwordTextfield = passwordTextfield;

    }

}


