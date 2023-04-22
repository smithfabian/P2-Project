package main.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.app.models.NewOrderModel;

import java.sql.Date;

public class NewOrderController {
    @FXML
    Button addButton;
    @FXML
    Button cancelButton;
    @FXML
    TextField IDField;
    @FXML
    DatePicker dateField;
    @FXML
    ComboBox<String> customerIDField;
    @FXML
    TextField postalCodeField;
    @FXML
    Label addedLabel;
    Stage stage;
    NewOrderModel newOrderModel;
    public NewOrderController() {
        this.newOrderModel = new NewOrderModel();

    }
    @FXML
    public void initialize() {
        fillCustomerField();
    }
    private void fillCustomerField() {
        customerIDField.setItems(newOrderModel.getCustomers());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void cancelButtonClicked() {
        stage.close();
    }

    public void addButtonClicked() {
        if (!IDField.getText().isEmpty() && customerIDField.getValue() != null && dateField.getValue() != null ) {
            newOrderModel.setDate(Date.valueOf(dateField.getValue()));
            newOrderModel.setCustomerID(customerIDField.getValue());
            newOrderModel.setPostalCode(postalCodeField.getText());
            newOrderModel.setID(IDField.getText());
            newOrderModel.addToDataBase();
            addedLabel.setText(newOrderModel.getAddedLabel());
            IDField.clear();

        }
        else {
            addedLabel.setText("Please put information into required fields");
        }

    }
}
