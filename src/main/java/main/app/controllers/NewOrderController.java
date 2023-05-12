package main.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.app.models.NewOrderModel;
import main.app.models.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;

public class NewOrderController {
    private static final Logger logger = LogManager.getLogger(NewOrderController.class.getName());
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
        if (Session.getLoggedInUser() == 30) {
            logger.info("Usability test new order added");
        }
        if (customerIDField.getValue() != null && dateField.getValue() != null ) {
            newOrderModel.setDate(Date.valueOf(dateField.getValue()));
            newOrderModel.setCustomerID(customerIDField.getValue());
            newOrderModel.setPostalCode(postalCodeField.getText());
            newOrderModel.addToDataBase();
            addedLabel.setText(newOrderModel.getAddedLabel());

        }
        else {
            if (Session.getLoggedInUser() == 30) {
                logger.info("Usability test new order added FAILED");
            }
            addedLabel.setText("Please put information into required fields");
        }

    }
}
