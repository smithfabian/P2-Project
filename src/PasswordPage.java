import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class PasswordPage {
    public static void createPasswordScene(Stage stage) {
        BorderPane root = new BorderPane();

        // upper pane
        HBox upperPane = new HBox(10);

        // upper left Pane
        VBox upperLeftPane = new VBox(10);
        Text password1Text = new Text("New password");
        PasswordField password1Field = new PasswordField();
        upperLeftPane.getChildren().addAll(password1Text, password1Field);

        // upper right Pane
        VBox upperRightPane = new VBox(10);
        Text password2Text = new Text("Repeat new password");
        PasswordField password2Field = new PasswordField();
        Label wrongPasswordPrompt = new Label();
        upperRightPane.getChildren().addAll(password2Text, password2Field, wrongPasswordPrompt);

        upperPane.getChildren().addAll(upperLeftPane, upperRightPane);

        // lower pane
        HBox buttonsPane = new HBox(10);
        buttonsPane.setAlignment(Pos.CENTER_RIGHT); // Set the alignment of the HBox to center
        Button saveButton = new Button("OK");
        Button cancelButton = new Button("Cancel");
        cancelButton.setCancelButton(true);
        double buttonWidth = 100;
        saveButton.setMinWidth(buttonWidth);
        cancelButton.setMinWidth(buttonWidth);
        buttonsPane.getChildren().addAll(saveButton, cancelButton);

        // button actions
        cancelButton.setOnAction(actionEvent -> {
            stage.close();
        });

        saveButton.setOnAction(actionEvent -> {
            if (password1Field.getText().equals("")) {
            wrongPasswordPrompt.setText("Enter a new password");
            wrongPasswordPrompt.setTextFill(Color.RED);
            }
            else if (password1Field.getText().equals(password2Field.getText())) {
                // TODO check password complexity
                // TODO save password to db
                stage.close();
            }
            else {
                wrongPasswordPrompt.setText("Passwords do not match");
                wrongPasswordPrompt.setTextFill(Color.RED);
            }
        });

        // layout
        VBox layoutPane = new VBox(10, upperPane, buttonsPane);
        root.setCenter(layoutPane);
        layoutPane.setPadding(new Insets(10));

        Scene logsScene = new Scene(root, 350, 130);
        stage.setResizable(false);
        stage.setScene(logsScene);
    }
}
