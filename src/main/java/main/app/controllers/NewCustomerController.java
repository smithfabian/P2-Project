package main.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.app.models.NewCustomerModel;
import main.app.models.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NewCustomerController {
    private static final Logger logger = LogManager.getLogger(NewCustomerController.class.getName());
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
            if (Session.getLoggedInUser() == 30) {
                logger.info("Usability test new customer added");
            }
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
            if (Session.getLoggedInUser() == 30) {
                logger.info("Usability test new customer added FAILED");
            }
            addedLabel.setText("Please fill in all required text fields");
            addedLabel.setTextFill(Color.RED);
        }
    }
}
