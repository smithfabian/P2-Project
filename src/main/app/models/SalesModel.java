package main.app.models;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

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

        public OrderRow(int orderID, Date date, int quantity, String customerID, int postalCode, String city) {
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

        public void setCustomerID(String customerID) {
            this.customerID = customerID;
        }
    }
    public class ItemRow {
        private String itemID;
        private String mainGroup;
        private String subGroup;
        private int totalBought;
        private int totalReturned;

        public ItemRow(String itemID, String mainGroup, String subGroup, int totalBought, int totalReturned) {
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

        public void setItemID(String value) {
            itemID = value;
        }

        public void setMainGroup(String value) {
            mainGroup = value;
        }

        public void setSubGroup(String value) {
            subGroup = value;
        }

        public void setTotalBought(int value) {
            totalBought = value;
        }

        public void setTotalReturned(int value) {
            totalReturned = value;
        }
    }
    public SalesModel() {
        createCustomerTable(0, "25", "");
        createOrderTable(0, "25", "");
        createItemTable(0, "25", "");
    }

    public void createItemTable(int offset, String strLimit, String searchTerm) {
        Integer limit = Integer.parseInt(strLimit);
        itemTable = FXCollections.observableArrayList();
        try {
            String query =
                    "SELECT i.ItemID, i.ItemMainGroup, i.ItemSubGroup, " +
                    "SUM(CASE WHEN o.InvoiceQty >= 0 THEN o.InvoiceQty ELSE 0 END) AS TotalBought, " +
                    "SUM(CASE WHEN o.InvoiceQty < 0 THEN o.InvoiceQty ELSE 0 END) AS TotalReturned " +
                    "FROM p2.items i " +
                    "LEFT JOIN p2.orderitems o ON i.ItemID = o.ItemID " +
                    "WHERE i.ItemID LIKE ? OR i.ItemMainGroup LIKE ? OR i.ItemSubGroup LIKE ? " +
                    "GROUP BY i.ItemID, i.ItemMainGroup, i.ItemSubGroup " +
                    "ORDER BY TotalBought DESC " +
                    "LIMIT ? OFFSET ?";
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, "%" + searchTerm + "%");
                stmt.setString(2, "%" + searchTerm + "%");
                stmt.setString(3, "%" + searchTerm + "%");
                stmt.setInt(4, limit);
                stmt.setInt(5, (offset)*limit);
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

    public void createOrderTable(int offset, String strLimit, String searchTerm) {
        Integer limit = Integer.parseInt(strLimit);
        orderTable = FXCollections.observableArrayList();
        try {
            String query =
                    "SELECT o.OrderId, o.InvoiceDate, o.AccountNum, o.PostalCode, o.City, " +
                    "SUM(oi.InvoiceQty) as InvoiceQty " +
                    "FROM p2.orders o " +
                    "LEFT JOIN p2.orderitems oi ON o.OrderId = oi.OrderId " +
                    "WHERE o.OrderId LIKE ? OR o.InvoiceDate LIKE ? OR o.AccountNum LIKE ? OR o.PostalCode LIKE ? OR o.city LIKE ? " +
                    "GROUP BY o.OrderId, o.InvoiceDate, o.AccountNum, o.PostalCode, o.City " +
                    "LIMIT ? OFFSET ?";
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, "%" + searchTerm + "%");
                stmt.setString(2, "%" + searchTerm + "%");
                stmt.setString(3, "%" + searchTerm + "%");
                stmt.setString(4, "%" + searchTerm + "%");
                stmt.setString(5, "%" + searchTerm + "%");
                stmt.setInt(6, limit);
                stmt.setInt(7, offset*limit);
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

    public void createCustomerTable(int offset, String strLimit, String searchTerm){
        Integer limit = Integer.parseInt(strLimit);
        customerTable = FXCollections.observableArrayList();
        try {
            String query =
                    "SELECT a.AccountNum, a.MarketMainSector, a.MarketSubSector, " +
                    "SUM(CASE WHEN o.InvoiceQty >= 0 THEN o.InvoiceQty ELSE 0 END) as TotalItemsBought, " +
                    "SUM(CASE WHEN o.InvoiceQty < 0 THEN o.InvoiceQty ELSE 0 END) as TotalItemsReturned " +
                    "FROM p2.accounts a " +
                    "LEFT JOIN p2.orderitems o ON a.AccountNum = o.AccountNum " +
                    "WHERE a.AccountNum LIKE ?OR a.MarketMainSector LIKE ? OR a.MarketSubSector LIKE ? " +
                    "GROUP BY a.AccountNum " +
                    "ORDER BY TotalItemsBought DESC LIMIT ? OFFSET ?";
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, "%" + searchTerm + "%");
                stmt.setString(2, "%" + searchTerm + "%");
                stmt.setString(3, "%" + searchTerm + "%");
                stmt.setInt(4, limit);
                stmt.setInt(5, offset*limit);
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

    public String getMaxPagesItemTable(String searchTerm, String strLimit) {
        String MaxPages = "Error";
        int limit = Integer.parseInt(strLimit);

        try {
            String query =
                    "SELECT COUNT(*) as MaxRows FROM (" +
                    "SELECT i.ItemID, i.ItemMainGroup, i.ItemSubGroup, " +
                    "SUM(CASE WHEN o.InvoiceQty >= 0 THEN o.InvoiceQty ELSE 0 END) AS TotalBought, " +
                    "SUM(CASE WHEN o.InvoiceQty < 0 THEN o.InvoiceQty ELSE 0 END) AS TotalReturned " +
                    "FROM p2.items i " +
                    "LEFT JOIN p2.orderitems o ON i.ItemID = o.ItemID " +
                    "WHERE i.ItemID LIKE ? OR i.ItemMainGroup LIKE ? OR i.ItemSubGroup LIKE ? " +
                    "GROUP BY i.ItemID, i.ItemMainGroup, i.ItemSubGroup " +
                    ") as T";
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, "%" + searchTerm + "%");
                stmt.setString(2, "%" + searchTerm + "%");
                stmt.setString(3, "%" + searchTerm + "%");
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int maxRows = rs.getInt("MaxRows");
                    MaxPages = Integer.toString(maxRows / limit + 1);
                }
            }
            finally {
                conn.close();
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return MaxPages;
    }

    public String getMaxPagesOrderTable(String searchTerm, String strLimit) {
        String MaxPages = "Error";
        int limit = Integer.parseInt(strLimit);
        try {
            String query =
                    "SELECT COUNT(*) as MaxRows FROM (" +
                    "SELECT o.OrderId, o.InvoiceDate, o.AccountNum, o.PostalCode, o.City, " +
                    "SUM(oi.InvoiceQty) as InvoiceQty " +
                    "FROM p2.orders o " +
                    "LEFT JOIN p2.orderitems oi ON o.OrderId = oi.OrderId " +
                    "WHERE o.OrderId LIKE ? OR o.InvoiceDate LIKE ? OR o.AccountNum LIKE ? OR o.PostalCode LIKE ? OR o.city LIKE ?" +
                    "GROUP BY o.OrderId, o.InvoiceDate, o.AccountNum, o.PostalCode, o.City" +
                    ") as T";
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, "%" + searchTerm + "%");
                stmt.setString(2, "%" + searchTerm + "%");
                stmt.setString(3, "%" + searchTerm + "%");
                stmt.setString(4, "%" + searchTerm + "%");
                stmt.setString(5, "%" + searchTerm + "%");
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int maxRows = rs.getInt("MaxRows");
                    MaxPages = Integer.toString(maxRows / limit + 1);
                }
            } finally {
                conn.close();
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return MaxPages;
    }

    public String getMaxPagesCustomerTable(String searchTerm, String strLimit) {
        String MaxPages = "Error";
        int limit = Integer.parseInt(strLimit);
        try {
            String query =
                    "SELECT COUNT(*) as MaxRows FROM (" +
                    "SELECT a.AccountNum, a.MarketMainSector, a.MarketSubSector, " +
                    "SUM(CASE WHEN o.InvoiceQty >= 0 THEN o.InvoiceQty ELSE 0 END) as TotalItemsBought, " +
                    "SUM(CASE WHEN o.InvoiceQty < 0 THEN o.InvoiceQty ELSE 0 END) as TotalItemsReturned " +
                    "FROM p2.accounts a " +
                    "LEFT JOIN p2.orderitems o ON a.AccountNum = o.AccountNum " +
                    "WHERE a.AccountNum LIKE ?OR a.MarketMainSector LIKE ? OR a.MarketSubSector LIKE ? " +
                    "GROUP BY a.AccountNum, a.MarketMainSector, a.MarketSubSector " +
                    ") as T";
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, "%" + searchTerm + "%");
                stmt.setString(2, "%" + searchTerm + "%");
                stmt.setString(3, "%" + searchTerm + "%");
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int maxRows = rs.getInt("MaxRows");
                    MaxPages = Integer.toString(maxRows / limit + 1);
                }
            } finally {
                conn.close();
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return MaxPages;
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
