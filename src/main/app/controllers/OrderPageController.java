package main.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.app.models.OrderPageModel;
import main.app.models.SalesModel;

public class OrderPageController {
    Stage stage;
    OrderPageModel model;
    @FXML
    Label pageHeadline;
    @FXML
    TextField orderId;
    @FXML
    TextField date;
    @FXML
    TextField quantity;
    @FXML
    TextField customerID;
    @FXML
    TextField postalCode;
    @FXML
    TextField city;

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void setModelValues(SalesModel.OrderRow row) {
        model = new OrderPageModel(row);
        setLabelTexts();
        // fillCharts();

    }


    public void setLabelTexts() {
        orderId.setText(String.valueOf(model.getOrderID()));
        pageHeadline.setText("Order id " + model.getOrderID());
        date.setText(String.valueOf(model.getDate()));
        quantity.setText(String.valueOf(model.getQuantity()));
        customerID.setText(model.getCustomerId());
        postalCode.setText(String.valueOf(model.getPostalCode()));
        city.setText(model.getCity());
    }
    public void fillCharts() {
        /*
        List<Integer> boughtAxis = model.getBoughtAxis();
        List<Integer> returnedAxis = model.getReturnedAxis();
        List<String> dateAxis = model.getDateAxis();
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
         */
    }
}
