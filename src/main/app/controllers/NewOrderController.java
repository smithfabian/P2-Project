package main.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.app.models.NewOrderModel;
import main.app.models.PasswordModel;

public class NewOrderController {
    @FXML
    Button addButton;
    @FXML
    Button cancelButton;
    @FXML
    TextField IDField;
    @FXML
    TextField qtyField;
    @FXML
    TextField dateField;
    Stage stage;
    NewOrderModel newOrderModel;
    public NewOrderController() {
        this.newOrderModel = new NewOrderModel();
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
        stage.close();
    }

    public void addButtonClicked(ActionEvent actionEvent) {
        newOrderModel.addToDatabase();
    }
}
