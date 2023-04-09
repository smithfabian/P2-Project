package main.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.app.models.NewItemModel;

public class NewItemController {
    @FXML
    Button addButton;
    @FXML
    Button cancelButton;
    @FXML
    TextField IDField;
    @FXML
    TextField mainGroupField;
    @FXML
    TextField subGroupField;
    @FXML
    TextField priceField;
    Stage stage;
    NewItemModel newItemModel;

    public NewItemController() {
        this.newItemModel = new NewItemModel();
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void cancelButtonClicked(ActionEvent actionEvent) {
        stage.close();
    }

    public void addButtonClicked(ActionEvent actionEvent) {
        newItemModel.addToDatabase();
    }
}
