package main.app.models;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        private int orderID;
        private Date date;
        private int quantity;
        private String customerID;
        private int postalCode;
        private String city;

        private OrderRow(int orderID, Date date, int quantity, String customerID, int postalCode, String city) {
            this.orderID = orderID;
            this.date = date;
            this.quantity = quantity;
            this.customerID = customerID;
            this.postalCode = postalCode;
            this.city = city;
        }

        public int getOrderID() {
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

        public int getPostalCode() {
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
        createCustomerTable(0);
        createOrderTable(0);
        createItemTable(0);
    }

    public void createItemTable(int offset){
        createItemTable(offset, "");
    }

    public void createItemTable(int offset, String searchTerm) {
        itemTable = FXCollections.observableArrayList();
        try {
            String query =
                    "SELECT i.ItemID, i.ItemMainGroup, i.ItemSubGroup," +
                    " sum(if(o.InvoiceQty >= 0, o.InvoiceQty, 0)) as TotalBought," +
                    " sum(if(o.InvoiceQty < 0, o.InvoiceQty, 0)) AS TotalReturned" +
                    " from p2.items i LEFT JOIN p2.orderitems o USING (ItemID)" +
                    " WHERE i.ItemID LIKE ? OR i.ItemMainGroup LIKE ? OR i.ItemSubGroup LIKE ? group by i.ItemID" +
                    " order by TotalBought desc limit 1000 offset ?";
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, "%" + searchTerm + "%");
                stmt.setString(2, "%" + searchTerm + "%");
                stmt.setString(3, "%" + searchTerm + "%");
                stmt.setInt(4, offset*1000);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    itemTable.add(new ItemRow(rs.getString("ItemID"), rs.getString("ItemMainGroup"), rs.getString("ItemSubGroup"), rs.getInt("TotalBought"), -rs.getInt("TotalReturned")));
                }
            }
            finally {
                conn.close();
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOrderTable(int offset) {
        createOrderTable(offset, "");
    }

    public void createOrderTable(int offset, String searchTerm) {
        orderTable = FXCollections.observableArrayList();
        try {
            String query =
                    "SELECT o.OrderId," +
                    " o.InvoiceDate," +
                    " o.AccountNum," +
                    " o.PostalCode," +
                    " o.City," +
                    " sum(oi.InvoiceQty) as InvoiceQty" +
                    " from p2.orders o LEFT JOIN p2.orderitems oi USING (OrderId)" +
                    " WHERE o.OrderId LIKE ? OR o.InvoiceDate LIKE ? OR o.AccountNum LIKE ? OR o.PostalCode LIKE ? OR o.PostalCode LIKE ?" +
                    " group by OrderId, InvoiceDate, AccountNum, PostalCode, City" +
                    " limit 1000 offset ?";
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, "%" + searchTerm + "%");
                stmt.setString(2, "%" + searchTerm + "%");
                stmt.setString(3, "%" + searchTerm + "%");
                stmt.setString(4, "%" + searchTerm + "%");
                stmt.setString(5, "%" + searchTerm + "%");
                stmt.setInt(6, offset*1000);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    orderTable.add(new OrderRow(Integer.parseInt(rs.getString("OrderId")), rs.getDate("InvoiceDate"), rs.getInt("InvoiceQty"), rs.getString("AccountNum"), rs.getInt("PostalCode"), rs.getString("City") ));
                }
            } finally {
                conn.close();
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCustomerTable(int offset){
        createCustomerTable(offset, "");
    }

    public void createCustomerTable(int offset, String searchTerm){
        customerTable = FXCollections.observableArrayList();
        try {
            String query = "SELECT " +
                    "a.AccountNum, " +
                    "a.MarketMainSector," +
                    "a.MarketSubSector, " +
                    "sum(if (o.InvoiceQty >= 0, o.InvoiceQty,0)) as TotalItemsBought, " +
                    "sum(if (o.InvoiceQty < 0, o.InvoiceQty,0)) as TotalItemsReturned from p2.accounts a" +
                    " LEFT JOIN p2.orderitems o on o.AccountNum = a.AccountNum " +
                    " WHERE a.AccountNum LIKE ? OR MarketMainSector LIKE ? OR MarketSubSector LIKE ? group by a.AccountNum" +
                    " order by TotalItemsBought desc limit 1000 offset ?";
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, "%" + searchTerm + "%");
                stmt.setString(2, "%" + searchTerm + "%");
                stmt.setString(3, "%" + searchTerm + "%");
                stmt.setInt(4, offset*1000);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    customerTable.add(new CustomerRow(rs.getString("AccountNum"), rs.getString("MarketMainSector"), rs.getString("MarketSubSector"), rs.getInt("TotalItemsBought"), -rs.getInt("TotalItemsReturned") ));
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

    public ObservableList<ItemRow> getItemTable() {
        return itemTable;
    }


}
