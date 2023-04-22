package main.app.models;

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

    public void createChartData() {
        dateAxis = new ArrayList<>();
        boughtAxis = new ArrayList<>();
        returnedAxis = new ArrayList<>();
        try {
            String query = "SELECT InvoiceDate, sum(if(InvoiceQty >= 0, InvoiceQty, 0)) as boughtOnDay, sum(if(InvoiceQty < 0, InvoiceQty, 0)) AS returnedOnDay" +
                    " from p2.invoiceregister where ItemID = ? group by InvoiceDate order by InvoiceDate";
            Connection conn = DatabaseConnection.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1,itemID);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    dateAxis.add(rs.getString("InvoiceDate"));
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
