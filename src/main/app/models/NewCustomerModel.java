package main.app.models;

import java.io.IOException;
import java.sql.*;

public class NewCustomerModel {
    private String ID;
    private String mainSector;
    private String subSector;
    public void addToDatabase() {
        String query = "INSERT INTO p2.accounts (AccountNum,MarketMainSector,MarketSubSector,MarketMainSectorID,MarketSubSectorID) values (?,?,?,null,null)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,ID);
            stmt.setString(2,mainSector);
            stmt.setString(3,subSector);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setMainSector(String mainSector) {
        this.mainSector = mainSector;
    }

    public void setSubSector(String subSector) {
        this.subSector = subSector;
    }
}
