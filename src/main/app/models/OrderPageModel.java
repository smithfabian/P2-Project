package main.app.models;

import java.sql.*;

public class OrderPageModel {
    private int orderID;
    private Date date;
    private int quantity;
    private String customerId;
    private int postalCode;
    private String city;
    /*
    private List<Integer> boughtAxis;
    private List<Integer> returnedAxis;
    private List<String> dateAxis;
     */


    public OrderPageModel(SalesModel.OrderRow row) {
        this.orderID = row.getOrderID();
        this.date = row.getDate();
        this.quantity = row.getQuantity();
        this.customerId = row.getCustomerID();
        this.postalCode = row.getPostalCode();
        this.city = row.getCity();
        // createChartData();

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

    /*
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

     */
}
