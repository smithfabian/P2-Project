package main.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.app.models.CustomerPageModel;

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
    TableView<CustomerPageModel.customerPageRow> table;
    @FXML
    TableColumn<CustomerPageModel.customerPageRow,String> orderIDColumn;
    @FXML
    TableColumn<CustomerPageModel.customerPageRow, Date> invoiceDateColumn;
    @FXML
    TableColumn<CustomerPageModel.customerPageRow,Integer> invoiceQtyColumn;
    @FXML
    TableColumn<CustomerPageModel.customerPageRow,Integer> postalCodeColumn;
    @FXML
    TableColumn<CustomerPageModel.customerPageRow,String> cityColumn;


    public void setModelValues(String customerID) {
        customerPageModel = new CustomerPageModel(customerID);
        orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        invoiceDateColumn.setCellValueFactory(new PropertyValueFactory<>("InvoiceDate"));
        invoiceQtyColumn.setCellValueFactory(new PropertyValueFactory<>("InvoiceQty"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("City"));
        table.setItems(customerPageModel.getTable());
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
}
