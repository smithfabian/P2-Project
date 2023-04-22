package main.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import main.app.models.CustomerPageModel;

import java.util.Date;

public class CustomerPageController {
    Stage stage;
    CustomerPageModel customerPageModel;
    @FXML
    Label customerIDLabel;
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

    public void setModeValues(String customerID) {
        customerPageModel = new CustomerPageModel(customerID);

    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
