package main.app.models;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.sql.*;

public class SalesModel {
    ObservableList<CustomerRow> customerTable;
    ObservableList<OrderRow> orderTable;

    public class CustomerRow {
        private String ID;
        private String mainSector;
        private String subSector;
        private int totalBought;
        private int totalReturned;

        public CustomerRow(String ID, String mainSector, String subSector, int totalBought, int totalReturned) {
            this.ID = ID;
            this.mainSector = mainSector;
            this.subSector = subSector;
            this.totalBought = totalBought;
            this.totalReturned = totalReturned;

        }

        public int getTotalBought() {
            return totalBought;
        }

        public int getTotalReturned() {
            return totalReturned;
        }

        public String getID() {
            return ID;
        }

        public String getMainSector() {
            return mainSector;
        }


        public String getSubSector() {
            return subSector;
        }
    }

    public class OrderRow {
        private String orderID;
        private Date date;
        private int quantity;
        private String customerID;
        private String postalCode;
        private String city;

        private OrderRow(String orderID, Date date, int quantity, String customerID, String postalCode, String city) {
            this.orderID = orderID;
            this.date = date;
            this.quantity = quantity;
            this.customerID = customerID;
            this.postalCode = postalCode;
            this.city = city;
        }

        public String getOrderID() {
            return orderID;
        }

        public Date getDate() {
            return date;
        }

        public int getQuantity() {
            return quantity;
        }

        public String getCustomerID() {
            return customerID;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public String getCity() {
            return city;
        }
    }
    public SalesModel() {
        customerTable = FXCollections.observableArrayList();
        orderTable = FXCollections.observableArrayList();
        createCustomerTable();
        createOrderTable();
    }

    private void createOrderTable() {
        try {
            String query = "SELECT p2.orders.OrderId," +
                    "p2.orders.InvoiceDate," +
                    " p2.orders.AccountNum," +
                    "p2.orders.PostalCode," +
                    " p2.orders.City," +
                    " sum(p2.orderitems.InvoiceQty) as InvoiceQty" +
                    " from p2.orders join p2.orderitems on p2.orderitems.OrderId=p2.orders.OrderId group by OrderId, InvoiceDate, AccountNum, PostalCode, City";
            Connection conn = DatabaseConnection.getConnection();
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    orderTable.add(new OrderRow(rs.getString("OrderId"), rs.getDate("InvoiceDate"), rs.getInt("InvoiceQty"), rs.getString("AccountNum"), rs.getString("PostalCode"), rs.getString("City") ));
                }
            } finally {
                conn.close();
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private void createCustomerTable(){
        try {
            String query = "SELECT " +
                    "p2.accounts.AccountNum, " +
                    "p2.accounts.MarketMainSector," +
                    "p2.accounts.MarketSubSector, " +
                    "sum(p2.orderitems.InvoiceQty) as TotalItemsBought from p2.accounts" +
                    " join p2.orderitems on p2.orderitems.AccountNum = p2.accounts.AccountNum AND InvoiceQty >= 0 group by AccountNum";
            Connection conn = DatabaseConnection.getConnection();
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    customerTable.add(new CustomerRow(rs.getString("AccountNum"), rs.getString("MarketMainSector"), rs.getString("MarketSubSector"), rs.getInt("TotalItemsBought"), 0 ));
                }
            } finally {
                conn.close();
            }
        }
                catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<CustomerRow> getCustomerTable() {
        return customerTable;
    }

    public ObservableList<OrderRow> getOrderTable() {
        return orderTable;
    }
}
