package main.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.util.Optional;

public class ChangeUserController {
    @FXML
    public TextField Username_textfield;

    @FXML
    public TextField Password_textfield;

    @FXML
    public TextField UserID_textfield;
    @FXML
    public Button save;

    //save button clicked
    public void saveButtonClicked() {
        try {
            Alert alert;

            if (Username_textfield.getText().isEmpty() || Password_textfield.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a new username and password");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Conformation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to change/add user: " + Username_textfield.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    // CONNECT TO DATABASE TO WRITE TO IT
                    //TODO
                }


                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("User successfully added/changed!");
                alert.showAndWait();

                //update the tableview with new username and id- call method addUserListData
                //TODO



            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
