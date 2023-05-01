package main.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.app.models.ChangeUserModel;
import main.app.models.PasswordManager;

import java.sql.SQLException;
import java.util.Optional;

public class ChangeUserController {
    Stage stage;
    @FXML
    public TextField Username_textfield;
    @FXML
    public PasswordField Repeat_password;
    @FXML
    public PasswordField Password_textfield;
    @FXML
    public TextField UserID_textfield;
    @FXML
    public Button save;

    @FXML
    public CheckBox adminCheckBox;
    private AddDelController addDelController;

    ChangeUserModel changeUserModel;
    public ChangeUserController() {
        this.changeUserModel = new ChangeUserModel();
    }

    //back button clicked
    @FXML
    private void backButtonClicked() {
         stage.close();

    }

    //save button clicked
    public void saveButtonClicked() {
        try {
            Alert alert;

            if (Username_textfield.getText().isEmpty() || Password_textfield.getText().isEmpty()|| Repeat_password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a new username and password");
                alert.showAndWait();
                Optional<ButtonType> option = alert.showAndWait();

            } else if (Password_textfield.getText().equals(Repeat_password.getText())){
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Conformation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to change/add user: " + Username_textfield.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.CANCEL)){
                    alert.showAndWait();
                }
                if (option.get().equals(ButtonType.OK)) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("User successfully added/changed!");
                    alert.showAndWait();
                    stage.close();
                    SaveUserToDatabase();
                    adminCheckBoxIsChecked();

                }

            } else if (!Password_textfield.getText().equals(Repeat_password.getText())){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("The passwords do not match");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
            }
    }

    // Will save the newly created user to the database table.
    public void SaveUserToDatabase() {
        changeUserModel.setUsername_textfield(Username_textfield.getText());
        changeUserModel.setPassword_textfield(PasswordManager.generateHash(Password_textfield.getText()));
        changeUserModel.getUserIntoTable();
        addDelController.updateTable();

    }


    // if admin checkbox is checked then store it as 1 in the database
    public void adminCheckBoxIsChecked() throws SQLException {

        changeUserModel.setIsAdmin(adminCheckBox.isSelected());
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setAddDelController(AddDelController addDelController) {
        this.addDelController = addDelController;
    }
}
