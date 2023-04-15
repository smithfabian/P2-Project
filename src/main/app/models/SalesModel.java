package main.app.models;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.sql.*;

public class SalesModel {
    private ObservableList<CustomerRow> customerTable;
    private ObservableList<OrderRow> orderTable;

    private ObservableList<ItemRow> itemTable;




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
    public class ItemRow {
        private String itemID;
        private String mainGroup;
        private String subGroup;
        private int totalBought;
        private int totalReturned;

        private ItemRow(String itemID, String mainGroup, String subGroup, int totalBought, int totalReturned) {
            this.itemID = itemID;
            this.mainGroup = mainGroup;
            this.subGroup = subGroup;
            this.totalBought = totalBought;
            this.totalReturned = totalReturned;
        }

        public String getItemID() {
            return itemID;
        }

        public String getMainGroup() {
            return mainGroup;
        }

        public String getSubGroup() {
            return subGroup;
        }

        public int getTotalBought() {
            return totalBought;
        }

        public int getTotalReturned() {
            return totalReturned;
        }
    }
    public SalesModel() {
        customerTable = FXCollections.observableArrayList();
        orderTable = FXCollections.observableArrayList();
        itemTable = FXCollections.observableArrayList();
        createCustomerTable();
        createOrderTable();
        createItemTable();
    }
    private void createItemTable() {
        try {
            String query = "SELECT i.ItemID," +
                    " i.ItemMainGroup," +
                    " i.ItemSubGroup," +
                    " sum(if(o.InvoiceQty >= 0, o.InvoiceQty, 0)) as TotalBought," +
                    " sum(if(o.InvoiceQty < 0, o.InvoiceQty, 0)) AS TotalReturned" +
                    " from p2.items i join p2.orderitems o on o.ItemID=i.ItemID group by i.ItemID ";
            DatabaseConnection db = new DatabaseConnection();
            Connection conn = db.getConnection();
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    itemTable.add(new ItemRow(rs.getString("ItemID"), rs.getString("ItemMainGroup"), rs.getString("ItemSubGroup"), rs.getInt("TotalBought"), -rs.getInt("TotalReturned")));
                }
            } finally {
                conn.close();
            }
        }
        catch(IOException | SQLException e) {
            e.printStackTrace();
        }
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
            DatabaseConnection db = new DatabaseConnection();
            Connection conn = db.getConnection();
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    orderTable.add(new OrderRow(rs.getString("OrderId"), rs.getDate("InvoiceDate"), rs.getInt("InvoiceQty"), rs.getString("AccountNum"), rs.getString("PostalCode"), rs.getString("City") ));
                }
            } finally {
                conn.close();
            }
        }
        catch(IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void createCustomerTable(){
        try {
            String query = "SELECT " +
                    "a.AccountNum, " +
                    "a.MarketMainSector," +
                    "a.MarketSubSector, " +
                    "sum(if (o.InvoiceQty >= 0, o.InvoiceQty,0)) as TotalItemsBought, " +
                    "sum(if (o.InvoiceQty < 0, o.InvoiceQty,0)) as TotalItemsReturned from p2.accounts a" +
                    " join p2.orderitems o on o.AccountNum = a.AccountNum group by a.AccountNum";
            DatabaseConnection db = new DatabaseConnection();
            Connection conn = db.getConnection();
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    customerTable.add(new CustomerRow(rs.getString("AccountNum"), rs.getString("MarketMainSector"), rs.getString("MarketSubSector"), rs.getInt("TotalItemsBought"), -rs.getInt("TotalItemsReturned") ));
                }
            } finally {
                conn.close();
            }
        }
                catch(IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<CustomerRow> getCustomerTable() {
        return customerTable;
    }

    public ObservableList<OrderRow> getOrderTable() {
        return orderTable;
    }

    public ObservableList<ItemRow> getItemTable() {
        return itemTable;
    }


}
