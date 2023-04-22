package main.app.models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CustomerPageModel {
    ObservableList<customerPageRow> table;
    private String customerID;
    private List<String> dateAxis;
    private List<Integer> boughtAxis;

    public List<Integer> getBoughtAxis() {
        return boughtAxis;
    }

    public List<String> getDateAxis() {
        return dateAxis;
    }

    public class customerPageRow {
        private int orderID;
        private Date invoiceDate;
        private int invoiceQty;
        private int postalCode;
        private String city;

        public int getOrderID() {
            return orderID;
        }

        public Date getInvoiceDate() {
            return invoiceDate;
        }

        public int getInvoiceQty() {
            return invoiceQty;
        }

        public int getPostalCode() {
            return postalCode;
        }

        public String getCity() {
            return city;
        }
        public customerPageRow(int orderID, Date invoiceDate, int invoiceQty, int postalCode, String city) {
            this.orderID = orderID;
            this.invoiceDate = invoiceDate;
            this.invoiceQty = invoiceQty;
            this.postalCode = postalCode;
            this.city = city;

        }
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
                    table.add(new customerPageRow(rs.getInt("OrderId"),date,totalQty,rs.getInt("PostalCode"),rs.getString("City")));
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

    public ObservableList<customerPageRow> getTable() {
        return table;
    }

    public String getCustomerID() {
        return customerID;
    }
}
