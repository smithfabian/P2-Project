package p2.ChangeUser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ChangeUserController {

    @FXML
    public TextField Username_textfield;

    @FXML
    public TextField Password_textfield;

    @FXML
    public TextField UserID_textfield;


    public void sceneToAddDel(ActionEvent event1) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/p2/AddDellUser/AddDel-view.fxml"));
        Stage stage = (Stage) ((Node) event1.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //method to save newly created username and password to the database table:

    //method to search for user ID in textfield:

    // method to create random generated Id to the newly created username and password

}
