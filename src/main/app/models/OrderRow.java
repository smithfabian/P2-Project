package main.app.models;

import java.sql.Date;

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