package p2.LoginPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    private LoginModel loginModel = new LoginModel();
    @FXML
    AnchorPane anchorPane;
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button loginButton;


    public LoginController(Stage stage) throws IOException {
        stage.setScene(new Scene(getView().load(),1000,500));
    }

    public LoginController() {

    }
    public FXMLLoader getView() {
        return new FXMLLoader(LoginController.class.getResource("/p2/LoginPage/Login-view.fxml"));
    }
    public void loginAttempt() throws IOException {
        loginModel.setUsername(usernameField.getText());
        loginModel.setPassword(passwordField.getText());
        if (loginModel.getUsername().equals("admin") && loginModel.getPassword().equals("admin")) {
            System.out.println("login");
        }
        else if (loginModel.getUsername().equals("user") && loginModel.getPassword().equals("password")) {
            new p2.EmployeePage.EmployeeController((Stage) anchorPane.getScene().getWindow());
        }
    }

}
