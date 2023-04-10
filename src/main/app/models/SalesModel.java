package main.app.models;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.sql.*;

public class SalesModel {
    ObservableList<CustomerRow> customerTable;

    public class CustomerRow {
        private String ID;
        private String mainSector;
        private String subSector;
        private String postalCode;
        private int totalBought;
        private int totalReturned;

        public CustomerRow(String ID, String mainSector, String subSector, String postalCode, int totalBought, int totalReturned) {
            this.ID = ID;
            this.mainSector = mainSector;
            this.subSector = subSector;
            this.postalCode = postalCode;
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

        public String getPostalCode() {
            return postalCode;
        }

        public String getSubSector() {
            return subSector;
        }
    }
    public SalesModel() {
        customerTable = FXCollections.observableArrayList();
        createTable();
    }

    private void createTable(){
        try {
            String query = "SELECT " +
                    "p2.accounts.AccountNum, " +
                    "p2.accounts.MarketMainSector," +
                    "p2.accounts.MarketSubSector, " +
                    "sum(p2.orderitems.InvoiceQty) as TotalItemsBought from p2.accounts" +
                    " join p2.orderitems on p2.orderitems.AccountNum = p2.accounts.AccountNum AND InvoiceQty >= 0 group by AccountNum";
            DatabaseConnection db = new DatabaseConnection();
            Connection conn = db.getConnection();
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                ResultSetMetaData metaData = rs.getMetaData();
                while (rs.next()) {
                    customerTable.add(new CustomerRow(rs.getString("AccountNum"), rs.getString("MarketMainSector"), rs.getString("MarketSubSector"), "Null", rs.getInt("TotalItemsBought"), 0 ));
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
}
