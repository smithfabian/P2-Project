package main.app.models;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminModel {

    public class BarChartData{
        private String xValue;
        private int yValue;

        public BarChartData(String xValue, int yValue){
            this.xValue = xValue;
            this.yValue = yValue;
        }

        public String getXValue(){
            return xValue;
        }

        public int getYValue(){
            return yValue;
        }
    }

    public List<BarChartData> getChartData(String someIdentifier) throws IOException, SQLException {
        List<BarChartData> listOfData = new ArrayList<>();
        String query = null;

        if (someIdentifier.equals("someIdentifier1")){
            query = "SELECT AccountNum, sum(InvoiceQty) as BoughtMinusReturns from p2.orderitems group by AccountNum order by BoughtMinusReturns DESC limit 5";
        } else if (someIdentifier.equals("someIdentifier2")) {
            //THIS NEEDS TO BE SOMETHING ELSE
            query = "SELECT AccountNum, sum(InvoiceQty) as BoughtMinusReturns from p2.orderitems group by AccountNum order by BoughtMinusReturns limit 5";
        }

        Connection conn = DatabaseConnection.getConnection();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                for (int i = 1; i <= 5; i++) {
                    String accoutNum = rs.getString("AccountNum");
                    int value = rs.getInt("BoughtMinusReturns");
                    listOfData.add(new BarChartData(accoutNum, value));
                }
            }
        }
        finally {
            conn.close();
        }
        return listOfData;
    }
}
