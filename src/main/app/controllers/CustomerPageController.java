package main.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.app.models.CustomerPageModel;
import main.app.models.SalesModel;
import main.app.views.OrderPageView;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class CustomerPageController {
    Stage stage;
    CustomerPageModel customerPageModel;
    @FXML
    LineChart<String, Integer> lineChart;
    @FXML
    Label customerIDLabel;
    @FXML
    TableView<SalesModel.OrderRow> table;
    @FXML
    TableColumn<SalesModel.OrderRow,String> orderIDColumn;
    @FXML
    TableColumn<SalesModel.OrderRow, Date> invoiceDateColumn;
    @FXML
    TableColumn<SalesModel.OrderRow,Integer> invoiceQtyColumn;
    @FXML
    TableColumn<SalesModel.OrderRow,Integer> postalCodeColumn;
    @FXML
    TableColumn<SalesModel.OrderRow,String> cityColumn;


    public void setModelValues(String customerID) {
        customerPageModel = new CustomerPageModel(customerID);
        orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        invoiceDateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        invoiceQtyColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("City"));
        table.setItems(customerPageModel.getTable());
        customerIDLabel.setText("Customer ID: " + customerPageModel.getCustomerID());
        List<Integer> boughtAxis = customerPageModel.getBoughtAxis();
        List<String> dateAxis = customerPageModel.getDateAxis();
        // Clear existing data
        lineChart.getData().clear();

        // Create new series
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        // Add data to series
        for (int i = 0; i < boughtAxis.size() ; i++) {
            series.getData().add(new XYChart.Data<>(dateAxis.get(i),boughtAxis.get((i))));

        }

        // Add series to bar charts
        lineChart.getData().add(series);


    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void orderRowClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            SalesModel.OrderRow row = table.getSelectionModel().getSelectedItem();
            row.setCustomerID(customerPageModel.getCustomerID());
            OrderPageView view = new OrderPageView(row);
            try {
                view.start(new Stage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void backButtonClicked() {
        stage.close();
    }
}
