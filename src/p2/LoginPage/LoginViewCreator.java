package p2.LoginPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginViewCreator {

    private final LoginModel model;
    private Runnable actionRunnable;
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button loginButton;

    public LoginViewCreator(LoginModel model, Runnable actionRunnable) {
        this.model = model;
        this.actionRunnable = actionRunnable;
    }

    public FXMLLoader getView() {
        return new FXMLLoader(LoginViewCreator.class.getResource("/p2/LoginPage/Login-view.fxml"));
    }


    public void loginAttempt(ActionEvent actionEvent) {
        actionRunnable.run();
    }
}
