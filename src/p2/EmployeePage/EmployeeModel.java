package p2.EmployeePage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;

public class EmployeeModel {

    private ObservableList<Integer> barChartUpperY = FXCollections.observableArrayList();
    private ObservableList<Integer> barChartUpperX = FXCollections.observableArrayList();
    private ObservableList<Integer> barChartLowerY = FXCollections.observableArrayList();
    private ObservableList<Integer> barChartLowerX = FXCollections.observableArrayList();

    public EmployeeModel() {
        barChartUpperY.addAll(10,20,30,40,50,60,70);
        barChartUpperX.addAll(10,20,30,40,50,60,70);
        barChartLowerY.addAll(10,20,30,40,50,60,70);
        barChartLowerX.addAll(10,20,30,40,50,60,70);
    }

    public ObservableList<Integer> getBarChartLowerX() {
        return barChartLowerX;
    }

    public ObservableList<Integer> getBarChartLowerY() {
        return barChartLowerY;
    }

    public ObservableList<Integer> getBarChartUpperY() {
        return barChartUpperY;
    }

    public ObservableList<Integer> getBarChartUpperX() {
        return barChartUpperX;
    }
}
