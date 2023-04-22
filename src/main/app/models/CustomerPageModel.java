package main.app.models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerPageModel {
    ObservableList<customerPageRow> table;
    private String customerID;

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
        fillTable();
    }
    public void fillTable() {
        table = FXCollections.observableArrayList();
        try {
            String query = "SELECT OrderID, InvoiceDate,sum(InvoiceQty) as TotalQty,PostalCode,City from p2.invoiceregisterwithorderid where AccountNum = ? group by OrderID order by TotalQty ";
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1,customerID);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    table.add(new customerPageRow(rs.getInt("OrderId"),new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("InvoiceDate")),rs.getInt("TotalQty"),rs.getInt("PostalCode"),rs.getString("City")));
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            } finally {
                conn.close();
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
}
