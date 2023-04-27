package main.app.models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CustomerPageModel {
    ObservableList<OrderRow> table;
    private String customerID;
    private List<String> dateAxis;
    private List<Integer> boughtAxis;

    public List<Integer> getBoughtAxis() {
        return boughtAxis;
    }

    public List<String> getDateAxis() {
        return dateAxis;
    }


    public CustomerPageModel(String customerID) {
        this.customerID = customerID;
        fillTableAndGraph();
    }
    public void fillTableAndGraph() {
        table = FXCollections.observableArrayList();
        dateAxis = new ArrayList<>();
        boughtAxis = new ArrayList<>();
        try {
            String query = "SELECT OrderID, InvoiceDateNew,sum(InvoiceQty) as TotalQty,PostalCode,City from p2.invoiceregisterwithorderid where AccountNum = ? group by OrderID,InvoiceDateNew,PostalCode,City order by InvoiceDateNew asc";
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1,customerID);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Date date = rs.getDate("InvoiceDateNew");
                    int totalQty = rs.getInt("TotalQty");
                    dateAxis.add(date.toString());
                    boughtAxis.add(totalQty);
                    table.add(new OrderRow(rs.getInt("OrderId"),date,totalQty,null,rs.getInt("PostalCode"),rs.getString("City")));
                }
            }
            finally {
                conn.close();
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<OrderRow> getTable() {
        return table;
    }

    public String getCustomerID() {
        return customerID;
    }
}
