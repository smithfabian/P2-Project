package main.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.app.models.NewCustomerModel;
import main.app.models.NewOrderModel;

public class NewCustomerController {
    @FXML
    Button addButton;
    @FXML
    Button cancelButton;
    @FXML
    TextField IDField;
    @FXML
    TextField mainSectorField;
    @FXML
    TextField subSectorField;
    @FXML
    TextField postalField;
    Stage stage;
    NewCustomerModel newCustomerModel;
    public NewCustomerController() {
        this.newCustomerModel = new NewCustomerModel();
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
        stage.close();
    }

    public void addButtonClicked(ActionEvent actionEvent) {
        newCustomerModel.addToDatabase();
    }
}
