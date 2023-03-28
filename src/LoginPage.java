
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.application.*;
import javafx.scene.control.Button;

public class LoginPage extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        VBox loginFields = new VBox();
        TextField usernameInput = new TextField();
        PasswordField passwordInput = new PasswordField();
        usernameInput.setMaxWidth(200);
        passwordInput.setMaxWidth(200);
        Label loginAttempt = new Label("");
        Button loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (usernameInput.getText().equals("admin") && passwordInput.getText().equals("admin")) {
                    loginAttempt.setText("Login successful");
                    loginAttempt.setTextFill(Color.GREEN);
                }
                else {
                    loginAttempt.setText("Log in unsuccessful");
                    loginAttempt.setTextFill(Color.RED);
                }
            }
        });
        loginButton.setMaxSize(100,100);
        loginFields.getChildren().addAll(usernameInput,passwordInput,loginButton,loginAttempt);
        loginFields.setAlignment(Pos.CENTER);
        Scene scene = new Scene(loginFields,1000,500);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
