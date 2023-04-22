package main.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.app.models.PasswordManager;
import main.app.models.Session;
import main.app.views.AdminView;
import main.app.models.LoginModel;
import main.app.views.EmployeeView;

import java.io.IOException;

public class LoginController {
    private LoginModel loginModel = new LoginModel();
    Stage stage;
    @FXML
    AnchorPane anchorPane;
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button loginButton;
    @FXML
    Label errorMessage;

    public LoginController() {
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void loginAttempt() {
        String password = passwordField.getText();
        String username = usernameField.getText();

        if (password.isEmpty() || username.isEmpty()){
            errorMessage.setText("Enter password and username");
        } else if (loginModel.getUserFromDB(username) && PasswordManager.isCorrectPassword(password, loginModel.getPassword())){
            Session.setLoggedInUser(loginModel.getUserId());

            if (loginModel.getIsAdmin()) {
                Session.setIsAdmin(true);
                AdminView view = new AdminView();
                try {
                    view.start(stage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                EmployeeView view = new EmployeeView();
                try {
                    view.start(stage);
                } catch (IOException ignored) {
                }
            }
        } else {
            errorMessage.setText("Incorrect credentials!");
            // TODO: insert log
        }
    }
}
