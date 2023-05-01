package main.app.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderPageModel {
    int orderID;
    Date date;
    int quantity;
    String customerId;
    int postalCode;
    String city;

    public OrderPageModel(OrderRow row) {
        this.orderID = row.getOrderID();
        this.date = row.getDate();
        this.quantity = row.getQuantity();
        this.customerId = row.getCustomerID();
        this.postalCode = row.getPostalCode();
        this.city = row.getCity();
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

    public String getCustomerId() {
        return customerId;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public ObservableList<orderItemRow> getTable() {
        ObservableList<orderItemRow> table = FXCollections.observableArrayList();
            String query = "SELECT * from p2.orderitems oi LEFT JOIN p2.items i ON oi.ItemID=i.ItemID WHERE oi.OrderId = ?";
            try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, this.getOrderID());
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    table.add(new orderItemRow(
                                    rs.getString("ItemID"),
                                    rs.getInt("InvoiceQty"),
                                    rs.getString("ItemMainGroup"),
                                    rs.getString("ItemSubGroup")
                            )
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return table;
    }

    public int updateOrderItems(Integer quantity, Integer orderID, String itemID) {
        int updatedColumns = 0;
        String query = "UPDATE p2.orderitems SET InvoiceQty = ? WHERE OrderId = ? AND ItemID = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, orderID);
            stmt.setString(3, itemID);
            updatedColumns = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;
        return updatedColumns;
    }

    public int updateOrder(Date InvoiceDate, String AccountNum, String City, int PostalCode) {
        int updatedColumns = 0;
        String query = "UPDATE p2.orders SET InvoiceDate = ?, AccountNum = ?, City = ?, PostalCode = ? WHERE OrderId = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, InvoiceDate);
            stmt.setString(2, AccountNum);
            stmt.setString(3, City);
            stmt.setInt(4, PostalCode);
            stmt.setInt(5, this.getOrderID());
            updatedColumns = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;
        return updatedColumns;
    }

    public int deleteOrder() {
        int noDeletedRows = 0;
        String query = "DELETE FROM p2.orders WHERE OrderId = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, this.getOrderID());
            noDeletedRows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return noDeletedRows;
    }

    public int insertOrderItems(Integer quantity, String itemId) {
        int insertedRows = 0;
        String query = "Insert Into p2.orderitems (ItemID, InvoiceQty, OrderId, AccountNum) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, itemId);
            stmt.setInt(2, quantity);
            stmt.setInt(3, this.getOrderID());
            stmt.setString(4, this.getCustomerId());
            insertedRows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertedRows;
    }

    public static class orderItemRow {
        TextField itemId;
        Integer originalQty;
        TextField itemQty;
        String itemMainGroup;
        String itemSubGroup;
        boolean isNewRow = false;

        public orderItemRow(String itemID, int invoiceQty, String itemMainGroup, String itemSubGroup) {
            this.itemId = new TextField(itemID);
            this.itemId.setDisable(true);
            this.originalQty = invoiceQty;
            this.itemQty = new TextField(String.valueOf(originalQty));
            this.itemMainGroup = itemMainGroup;
            this.itemSubGroup = itemSubGroup;
        }
        public TextField getItemId(){
            return this.itemId;
        }
        public TextField getItemQty(){
            return itemQty;
        }
        public String getItemMainGroup(){
            return this.itemMainGroup;
        }
        public Integer getOriginalQty(){
            return this.originalQty;
        }
        public String getItemSubGroup(){
            return this.itemSubGroup;
        }
        public void setItemIdDisable(boolean b){
            this.itemId.setDisable(b);
        }
        public void setIsNewRow(boolean b){
            this.isNewRow = b;
        }
        public boolean getIsNewRow(){
            return this.isNewRow;
        }

        public SalesModel.ItemRow getSalesPageItemRow() {
            SalesModel salesModel = new SalesModel();
            SalesModel.ItemRow itemRow = null;
            try {
                String query =
                        "SELECT i.ItemID, i.ItemMainGroup, i.ItemSubGroup, " +
                                "SUM(CASE WHEN o.InvoiceQty >= 0 THEN o.InvoiceQty ELSE 0 END) AS TotalBought, " +
                                "SUM(CASE WHEN o.InvoiceQty < 0 THEN o.InvoiceQty ELSE 0 END) AS TotalReturned " +
                                "FROM p2.items i " +
                                "LEFT JOIN p2.orderitems o ON i.ItemID = o.ItemID " +
                                "WHERE i.ItemID = ? " +
                                "GROUP BY i.ItemID, i.ItemMainGroup, i.ItemSubGroup";
                Connection conn = DatabaseConnection.getConnection();
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, this.itemId.getText());
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        itemRow = salesModel.new ItemRow(rs.getString("ItemID"), rs.getString("ItemMainGroup"), rs.getString("ItemSubGroup"), rs.getInt("TotalBought"), -rs.getInt("TotalReturned"));
                    }
                } finally {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return itemRow;
        }
    }
}
