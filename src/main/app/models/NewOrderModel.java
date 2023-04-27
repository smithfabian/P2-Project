package main.app.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class NewOrderModel {

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
        try (Connection conn = DatabaseConnection.getConnection()) {
            String insertQuery = "INSERT INTO p2.orders (InvoiceDate,AccountNum,postalCode) values (?,?,?)";
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                        insertStmt.setDate(1, date);
                        insertStmt.setString(2, customerID);
                        insertStmt.setString(3, postalCode);
                        insertStmt.executeUpdate();
                        addedLabel = "Order successfully added to database";
                }
            }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getAllCustomers() {
        try {
            String query = "SELECT a.AccountNum from p2.accounts a";
            try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    customers.add(rs.getString("AccountNum"));

                }
            }
        }
        catch(SQLException e) {
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


    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddedLabel() {
        return addedLabel;
    }
}
