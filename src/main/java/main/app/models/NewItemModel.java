package main.app.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewItemModel {
    private String ID;
    private String mainGroup;
    private String subGroup;
    private String addedLabel;
    private static final Logger logger = LogManager.getLogger(NewItemModel.class.getName());

    public void addToDatabase() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String checkQuery = "SELECT COUNT(*) FROM p2.items WHERE ItemID = ?";
            String insertQuery = "INSERT INTO p2.items (ItemID,ItemMainGroup,ItemSubGroup) values (?,?,?)";

            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setString(1, ID);
                try (ResultSet resultSet = checkStmt.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) == 0) {
                        try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                            insertStmt.setString(1, ID);
                            insertStmt.setString(2, mainGroup);
                            insertStmt.setString(3, subGroup);
                            insertStmt.executeUpdate();
                            addedLabel = "Item successfully added to database";
                            logger.info("User " + Session.getLoggedInUser() + ": New item added with ID " + ID);
                        }
                    } else {
                        logger.warn("User " + Session.getLoggedInUser() +  ": Item with " + ID + "already exists in database");
                        addedLabel = "This Item ID already exists in the database";
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setMainGroup(String mainGroup) {
        this.mainGroup = mainGroup;
    }

    public void setSubGroup(String subGroup) {
        this.subGroup = subGroup;
    }

    public String getAddedLabel() {
        return addedLabel;
    }
}
