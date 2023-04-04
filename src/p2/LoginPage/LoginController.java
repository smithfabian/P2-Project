package p2.LoginPage;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class LoginController {
    private LoginModel loginModel = new LoginModel();
    private Stage stage;
    private LoginViewCreator viewCreator;

    public LoginController(Stage stage) {
        this.stage = stage;
        this.viewCreator = new LoginViewCreator(loginModel, this::loginAttempt);
    }
    public FXMLLoader getView() {
        return viewCreator.getView();
    }
    public void loginAttempt() {
        if (loginModel.getUsername().equals("admin") && loginModel.getPassword().equals("admin")) {
            System.out.println("login");
        }
        else if (loginModel.getUsername().equals("username") && loginModel.getPassword().equals("password")) {
            System.out.println("login2");
        }
    }
}
