package main.app.models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CustomerPageModel {
    ObservableList<SalesModel.OrderRow> table;
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
        SalesModel salesModel = new SalesModel();
        table = FXCollections.observableArrayList();
        dateAxis = new ArrayList<>();
        boughtAxis = new ArrayList<>();
        try {
            String query = "SELECT o.OrderID, o.InvoiceDate,sum(i.InvoiceQty) as TotalQty,o.PostalCode,o.City from p2.orders o join p2.orderitems i on o.OrderId=i.OrderId where o.AccountNum = ? group by o.OrderID, o.InvoiceDate order by o.InvoiceDate asc";
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1,customerID);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Date date = rs.getDate("InvoiceDate");
                    int totalQty = rs.getInt("TotalQty");
                    dateAxis.add(date.toString());
                    boughtAxis.add(totalQty);
                    table.add(salesModel.new OrderRow(rs.getInt("OrderId"),date,totalQty,null,rs.getInt("PostalCode"),rs.getString("City")));
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

    public ObservableList<SalesModel.OrderRow> getTable() {
        return table;
    }

    public String getCustomerID() {
        return customerID;
    }
}
