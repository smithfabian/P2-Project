package main.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.app.models.PasswordModel;
import main.app.models.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PasswordController {
    private static final Logger logger = LogManager.getLogger(PasswordController.class.getName());
    Stage stage;
    PasswordModel model;
    @FXML
    TextField newPassword;
    @FXML
    TextField repeatPassword;
    @FXML
    Button okButton;
    @FXML
    Button cancelButton;
    @FXML
    Text validationText;


    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setModel(PasswordModel model){
        this.model = model;
    }


    public void okButtonClicked(){
        if (newPassword.getText().equals("")) {
            validationText.setText("Enter a new password");
        }
        else if (newPassword.getText().equals(repeatPassword.getText())) {
            if (Session.getLoggedInUser() == 30) {
                logger.info("Usability test changed password");
            }
            // TODO check password complexity
            model.setPassword(newPassword.getText());
            model.updatePassword();
            stage.close();
        }
        else {
            if (Session.getLoggedInUser() == 30) {
                logger.info("Usability test changed password FAILED");
            }
            validationText.setText("Passwords do not match");
        }
    }

    public void cancelButtonClicked(){
        stage.close();
    }

}
