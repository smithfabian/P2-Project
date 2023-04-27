package main.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.app.models.ItemPageModel;
import main.app.models.SalesModel;
import java.util.List;

public class ItemPageController {
    Stage stage;
    ItemPageModel itemPageModel;
    @FXML
    Label mainGroup;
    @FXML
    Label subGroup;
    @FXML
    Label totalBought;
    @FXML
    Label totalReturned;
    @FXML
    LineChart<String,Integer> boughtChart;
    @FXML
    LineChart<String, Integer> returnedChart;

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void setModelValues(SalesModel.ItemRow row) {
        itemPageModel = new ItemPageModel(row);
        setLabelText();
        fillCharts();

    }

    public void setLabelText() {
        mainGroup.setText("Item main group: " + itemPageModel.getMainGroup());
        subGroup.setText("Item sub group: " + itemPageModel.getSubGroup());
        totalBought.setText("Total bought: " + itemPageModel.getTotalBought());
        totalReturned.setText("Total returned: " + itemPageModel.getTotalReturned());
    }
    public void fillCharts() {
        List<Integer> boughtAxis = itemPageModel.getBoughtAxis();
        List<Integer> returnedAxis = itemPageModel.getReturnedAxis();
        List<String> dateAxis = itemPageModel.getDateAxis();
        // Clear existing data
        boughtChart.getData().clear();
        boughtChart.getData().clear();

        // Create new series
        XYChart.Series<String, Integer> boughtSeries = new XYChart.Series<>();
        XYChart.Series<String, Integer> returnedSeries = new XYChart.Series<>();

        // Add data to series
        for (int i = 0; i < boughtAxis.size() ; i++) {
            boughtSeries.getData().add(new XYChart.Data<>(dateAxis.get(i),boughtAxis.get((i))));
            returnedSeries.getData().add(new XYChart.Data<>(dateAxis.get(i),returnedAxis.get((i))));

        }

        // Add series to bar charts
        boughtChart.getData().add(boughtSeries);
        returnedChart.getData().add(returnedSeries);
    }

    public void backButtonClicked() {
        stage.close();
    }
}
