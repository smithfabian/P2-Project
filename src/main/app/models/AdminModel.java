package main.app.models;

import java.sql.ResultSetMetaData;

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
            query = "SELECT 1 as 'Category 1', 2 as 'Category 2', 3 as 'Category 3', 4 as 'Category 4', 5 as 'Category 5';";
        } else if (someIdentifier.equals("someIdentifier2")) {
            query = "SELECT 5 as 'Category 1', 4 as 'Category 2', 3 as 'Category 3', 2 as 'Category 4', 1 as 'Category 5';";
        }

        Connection conn = DatabaseConnection.getConnection();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String categoryName = metaData.getColumnName(i);
                    int value = rs.getInt(categoryName);
                    listOfData.add(new BarChartData(categoryName, value));
                }
            }
        }
        finally {
            conn.close();
        }
        return listOfData;
    }
}
