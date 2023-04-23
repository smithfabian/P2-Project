package main.app.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class OrderPageModel {
    int orderID;
    Date date;
    int quantity;
    String customerId;
    int postalCode;
    String city;

    public OrderPageModel(SalesModel.OrderRow row) {
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
            String query = "SELECT * from p2.orderitems JOIN p2.items USING(ItemID) WHERE OrderId = ?";
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

    public class orderItemRow {
        String itemId;
        int itemQty;
        String itemMainGroup;
        String itemSubGroup;

        public orderItemRow(String itemID, int invoiceQty, String itemMainGroup, String itemSubGroup) {
            this.itemId = itemID;
            this.itemQty = invoiceQty;
            this.itemMainGroup = itemMainGroup;
            this.itemSubGroup = itemSubGroup;
        }
        public String getItemId(){
            return this.itemId;
        }
        public int getItemQty(){
            return this.itemQty;
        }
        public String getItemMainGroup(){
            return this.itemMainGroup;
        }
        public String getItemSubGroup(){
            return this.itemSubGroup;
        }
    }
}
