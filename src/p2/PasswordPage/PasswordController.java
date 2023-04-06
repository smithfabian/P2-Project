package p2.PasswordPage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PasswordController {
    Stage stage;
    PasswordModel model;
    @FXML
    TextField newPassword;
    @FXML
    TextField repeatPassword;
    @FXML
    Button okButton;
    @FXML
    Button cancelButton;
    @FXML
    Text validationText;

    public PasswordController(){
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setModel(PasswordModel model){
        this.model = model;
    }

    public void okButtonClicked(){
        if (newPassword.getText().equals("")) {
            validationText.setText("Enter a new password");
        }
        else if (newPassword.getText().equals(repeatPassword.getText())) {
            // TODO check password complexity
            model.updatePassword();
            stage.close();
        }
        else {
            validationText.setText("Passwords do not match");
        }
    }

    public void cancelButtonClicked(){
        stage.close();
    }

}
