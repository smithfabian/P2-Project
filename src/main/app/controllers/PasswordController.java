package main.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.app.models.AdminModel;
import main.app.models.PasswordModel;

public class PasswordController {
    Stage stage;
    AdminController adminController;
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

    public void setAdminController(AdminController adminController){
        this.adminController = adminController;
    }

    public void okButtonClicked(){
        if (newPassword.getText().equals("")) {
            validationText.setText("Enter a new password");
        }
        else if (newPassword.getText().equals(repeatPassword.getText())) {
            // TODO check password complexity
            model.updatePassword();
            adminController.setIsPasswordWindowOpen(false);
            stage.close();
        }
        else {
            validationText.setText("Passwords do not match");
        }
    }

    public void cancelButtonClicked(){
        adminController.setIsPasswordWindowOpen(false);
        stage.close();
    }

}
