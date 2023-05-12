package main.app.models;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainPageModel {

    public class ChartData {
        private String xValue;
        private int yValue;

        public ChartData(String xValue, int yValue){
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

    public List<MainPageModel.ChartData> getBarChartData() throws IOException, SQLException {
        List<MainPageModel.ChartData> listOfData = new ArrayList<>();
        String query = "SELECT AccountNum, sum(InvoiceQty) as BoughtMinusReturns from p2.orderitems group by AccountNum order by BoughtMinusReturns DESC limit 5";;
        //THIS NEEDS TO BE SOMETHING ELSE


        Connection conn = DatabaseConnection.getConnection();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                for (int i = 1; i <= 5; i++) {
                    String accoutNum = rs.getString("AccountNum");
                    int value = rs.getInt("BoughtMinusReturns");
                    listOfData.add(new MainPageModel.ChartData(accoutNum, value));
                }
            }
        }
        finally {
            conn.close();
        }
        return listOfData;
    }
    public List<MainPageModel.ChartData> getLineChartData()  throws IOException, SQLException {
        List<MainPageModel.ChartData> listOfData = new ArrayList<>();
        String query = "SELECT o.InvoiceDate, sum(i.InvoiceQty) as ItemsSold from p2.orders o LEFT JOIN p2.orderitems i on o.OrderID = i.OrderID group by o.InvoiceDate order by o.InvoiceDate";;


        Connection conn = DatabaseConnection.getConnection();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                for (int i = 1; i <= 5; i++) {
                    String invoiceDate = rs.getDate("InvoiceDate").toString();
                    int value = rs.getInt("ItemsSold");
                    listOfData.add(new MainPageModel.ChartData(invoiceDate, value));
                }
            }
        }
        finally {
            conn.close();
        }
        return listOfData;
    }
}
