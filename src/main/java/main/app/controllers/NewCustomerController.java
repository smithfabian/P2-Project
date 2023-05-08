package main.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.app.models.NewCustomerModel;

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
    Label addedLabel;

    Stage stage;
    NewCustomerModel newCustomerModel;
    public NewCustomerController() {
        this.newCustomerModel = new NewCustomerModel();
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void cancelButtonClicked() {
        stage.close();
    }

    public void addButtonClicked() {
        if (!IDField.getText().isEmpty()) {
            newCustomerModel.setID(IDField.getText());
            newCustomerModel.setMainSector(mainSectorField.getText());
            newCustomerModel.setSubSector(subSectorField.getText());
            newCustomerModel.addToDatabase();
            addedLabel.setText("New customer added successfully");
            addedLabel.setTextFill(Color.GREEN);
            IDField.clear();
            mainSectorField.clear();
            subSectorField.clear();
        }
        else {
            addedLabel.setText("Please fill in all required text fields");
            addedLabel.setTextFill(Color.RED);
        }
    }
}
