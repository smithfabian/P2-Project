package main.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    Label addedLabel;
    Stage stage;
    NewItemModel newItemModel;

    public NewItemController() {
        this.newItemModel = new NewItemModel();
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void cancelButtonClicked() {
        stage.close();
    }

    public void addButtonClicked() {
        if (!IDField.getText().isEmpty()) {
            newItemModel.setID(IDField.getText());
            newItemModel.setMainGroup(mainGroupField.getText());
            newItemModel.setSubGroup(subGroupField.getText());
            newItemModel.setID(IDField.getText());
            newItemModel.addToDatabase();
            addedLabel.setText(newItemModel.getAddedLabel());
            IDField.clear();
            mainGroupField.clear();
            subGroupField.clear();
        }
        else {
            addedLabel.setText("Please put information into required fields");
        }
    }
}
