
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.application.*;
import javafx.scene.control.Button;

public class LoginPage extends Application {

    @Override
    public void start(Stage stage) {
        createLoginPage(stage);
        stage.setTitle("Sales Data Management System");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
    public static void createLoginPage(Stage stage) {
        VBox loginFields = new VBox(5);
        Label title = new Label("Sales Data Management System");
        title.setFont(Font.font("verdana", FontWeight.BOLD,20));
        TextField usernameInput = new TextField();
        PasswordField passwordInput = new PasswordField();
        usernameInput.setMaxWidth(200);
        passwordInput.setMaxWidth(200);
        Label loginAttempt = new Label("");
        Button loginButton = new Button("Login");
        loginButton.setOnAction(actionEvent -> {
            if (usernameInput.getText().equals("admin") && passwordInput.getText().equals("admin")) {
                loginAttempt.setText("Login successful");
                loginAttempt.setTextFill(Color.GREEN);
                AdminPage.createAdminScene(stage);
            }
            else if (usernameInput.getText().equals("user") && passwordInput.getText().equals("password")) {
                UsersPage.createUsersScene(stage);
            }
            else {
                loginAttempt.setText("Log in unsuccessful");
                loginAttempt.setTextFill(Color.RED);
            }
        });

        loginButton.setMaxSize(100,100);
        loginFields.getChildren().addAll(title,usernameInput,passwordInput,loginButton,loginAttempt);
        loginFields.setAlignment(Pos.CENTER);
        Scene scene = new Scene(loginFields,1000,500);
        stage.setScene(scene);
    }

}
