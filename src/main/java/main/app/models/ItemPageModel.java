package main.app.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemPageModel {
    private String itemID;
    private String mainGroup;
    private String subGroup;
    private int totalBought;
    private int totalReturned;
    private List<Integer> boughtAxis;
    private List<Integer> returnedAxis;
    private List<String> dateAxis;
    private static final Logger logger = LogManager.getLogger(OrderPageModel.class.getName());


    public ItemPageModel(SalesModel.ItemRow row) {
        this.itemID = row.getItemID();
        this.mainGroup = row.getMainGroup();
        this.subGroup = row.getSubGroup();
        this.totalBought = row.getTotalBought();
        this.totalReturned = row.getTotalReturned();
        createChartData();

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

    public List<String> getDateAxis() {
        return dateAxis;
    }

    public List<Integer> getBoughtAxis() {
        return boughtAxis;
    }

    public List<Integer> getReturnedAxis() {
        return returnedAxis;
    }

    public void updateItem(String newItemMainGroup, String newItemSubGroup){
        String query = "UPDATE p2.items SET ItemMainGroup = ?, ItemSubGroup = ? WHERE ItemID = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newItemMainGroup);
            stmt.setString(2, newItemSubGroup);
            stmt.setString(3, itemID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteItem(){
        String query = "DELETE FROM p2.items WHERE ItemID = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.getItemID());
            stmt.executeUpdate();
            logger.warn("User " + Session.getLoggedInUser() + ": Successfully deleted item with item ID + " + this.getItemID());

        } catch (SQLException e) {
            logger.warn("User " + Session.getLoggedInUser() + ": Failed to delete item with item ID + " + this.getItemID());
            e.printStackTrace();
        }
    }

    public void createChartData() {
        dateAxis = new ArrayList<>();
        boughtAxis = new ArrayList<>();
        returnedAxis = new ArrayList<>();
        try {
            String query = "SELECT o.InvoiceDate, sum(if(i.InvoiceQty >= 0, i.InvoiceQty, 0)) as boughtOnDay, sum(if(i.InvoiceQty < 0, i.InvoiceQty, 0)) AS returnedOnDay" +
                    " from p2.orders o join p2.orderitems i on o.OrderId = i.OrderId where ItemID = ? group by o.InvoiceDate order by o.InvoiceDate";
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1,itemID);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    dateAxis.add(rs.getDate("InvoiceDate").toString());
                    boughtAxis.add(rs.getInt("boughtOnDay"));
                    returnedAxis.add(rs.getInt("returnedOnDay"));
                }
            } finally {
                conn.close();
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
