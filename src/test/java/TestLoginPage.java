import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.app.controllers.LoginController;
import main.app.models.Session;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeoutException;

@ExtendWith(ApplicationExtension.class)
class TestLoginPage {
    private static final Properties secrets = loadSecrets();

    @Start
    private void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login-view.fxml"));
        Parent root = loader.load();
        LoginController controller = loader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(root);
        stage.setTitle("Sales data management system");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Helper function that returns secrets from a secrets file
     * @return Properties loaded secrets
     */
    private static Properties loadSecrets(){
        Properties secrets = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("secrets.properties");
            secrets.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return secrets;
    }

    // User story P2-37: https://cct-p2-group5.atlassian.net/browse/P2-37

    @Test
    void should_contain_text_field_with_prompts(FxRobot robot) throws TimeoutException {
        String actualUsernamePromptText = robot.lookup("#usernameField").queryAs(TextField.class).getPromptText();
        String expectedUsernamePromptText = "Enter your username";
        Assertions.assertThat(actualUsernamePromptText).isEqualTo(expectedUsernamePromptText);

        String actualPasswordPromptText = robot.lookup("#passwordField").queryAs(TextField.class).getPromptText();
        String expectedPasswordPromptText = "Enter your password";
        Assertions.assertThat(actualPasswordPromptText).isEqualTo(expectedPasswordPromptText);
    }

    @Test
    void should_contain_login_button_with_error_messages(FxRobot robot) {
        Button loginButton = robot.lookup("#loginButton").queryButton();
        Assertions.assertThat(loginButton).hasText("Login");

        robot.clickOn(loginButton);
        String actualErrorMessage = robot.lookup("#errorMessage").queryAs(Label.class).getText();
        String expectedErrorMessage = "Enter password and username";
        Assertions.assertThat(actualErrorMessage).isEqualTo(expectedErrorMessage);
    }

    @Test
    void should_login_as_admin(FxRobot robot) {
        Button loginButton = robot.lookup("#loginButton").queryButton();

        robot.lookup("#usernameField").queryAs(TextField.class).setText(secrets.getProperty("admin.username"));
        robot.lookup("#passwordField").queryAs(TextField.class).setText(secrets.getProperty("admin.password"));

        robot.clickOn(loginButton);
        Set<Button> buttons = robot.lookup(".button").queryAll();
        Assertions.assertThat(buttons.size()).isEqualTo(5);
        Assertions.assertThat(Session.getIsAdmin()).isTrue();
        Session.logout();
    }

    @Test
    void should_login_as_user(FxRobot robot) {
        Button loginButton = robot.lookup("#loginButton").queryButton();

        robot.lookup("#usernameField").queryAs(TextField.class).setText(secrets.getProperty("user.username"));
        robot.lookup("#passwordField").queryAs(TextField.class).setText(secrets.getProperty("user.password"));

        robot.clickOn(loginButton);
        Set<Button> buttons = robot.lookup(".button").queryAll();
        Assertions.assertThat(buttons.size()).isEqualTo(3);
        Assertions.assertThat(Session.getIsAdmin()).isFalse();
        Session.logout();
    }
}