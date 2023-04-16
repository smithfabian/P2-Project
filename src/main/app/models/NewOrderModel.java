package main.app.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.*;

public class NewOrderModel {

    private String ID;
    private Date date;
    private String customerID;
    private String postalCode;
    private String addedLabel;
    private ObservableList<String> customers;

    public NewOrderModel() {
        customers = FXCollections.observableArrayList();
        getAllCustomers();
    }

    public void addToDataBase() {
        try {
            DatabaseConnection db = new DatabaseConnection();
            try (Connection conn = db.getConnection()) {
                String checkQuery = "SELECT COUNT(*) FROM p2.orders WHERE OrderId = ?";
                String insertQuery = "INSERT INTO p2.orders (OrderID,InvoiceDate,AccountNum,postalCode) values (?,?,?,?)";

                try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                    checkStmt.setString(1, ID);
                    try (ResultSet resultSet = checkStmt.executeQuery()) {
                        if (resultSet.next() && resultSet.getInt(1) == 0) {
                            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                                insertStmt.setString(1, ID);
                                insertStmt.setDate(2, date);
                                insertStmt.setString(3, customerID);
                                insertStmt.setString(4, postalCode);
                                insertStmt.executeUpdate();
                                addedLabel = "Order successfully added to database";
                            }
                        } else {
                            addedLabel = "This order ID already exists in the database";
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getAllCustomers() {
        try {
            String query = "SELECT a.AccountNum from p2.accounts a";
            DatabaseConnection db = new DatabaseConnection();
            Connection conn = db.getConnection();
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    customers.add(rs.getString("AccountNum"));

                }
            } finally {
                conn.close();
            }
        }
        catch(IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<String> getCustomers() {
        return customers;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddedLabel() {
        return addedLabel;
    }
}
