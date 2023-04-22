package main.app.models;

import java.sql.*;

public class LoginModel {

    private String username = "";
    private String password = "";
    private Boolean isAdmin;
    private Integer userId;

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public String getPassword() {
        return password;
    }

    public Integer getUserId() {
        return userId;
    }

    public Boolean getUserFromDB(String username) {
        boolean result = false;
        try {
            String query = "SELECT p2.users.Id as id, p2.users.IsAdmin as isAdmin, p2.users.Password as password FROM p2.users WHERE p2.users.User = ?";
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            try {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    this.userId = rs.getInt("id");
                    this.isAdmin = rs.getInt("isAdmin") == 1;
                    this.password = rs.getString("password");
                    this.username = username;
                    result = true;
                }
            } finally {
                conn.close();
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
