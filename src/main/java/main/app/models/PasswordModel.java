package main.app.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PasswordModel {
    private String password;
    public void updatePassword(){
            String query = "UPDATE p2.users as u SET u.Password = ? where u.Id = ?";
            try (Connection conn = DatabaseConnection.getConnection()) {
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1,PasswordManager.generateHash(password));
                stmt.setInt(2,Session.getLoggedInUser());
                stmt.executeUpdate();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }


    }

    public void setPassword(String password) {
        this.password = password;
    }
}
