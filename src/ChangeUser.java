import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

// skal kunne gå tilbage til sidste scene hvis klikket på "back"
// skal kunne gemme ændringer af user og password
// skal kunne bekræfte "change in user"

public class ChangeUser extends Application  {
    @Override
    public void start(Stage stage) {
        // label for username and password
        Label username =  new Label("Username");
        Label password = new Label("password");


        // textfield for username and password
        TextField Username_field = new TextField();
        TextField Password_field = new TextField();

        // Button for save and back
        Button save_button = new Button("Save");
        Button back_button = new Button("Back");

        Label save  = new Label("");
        Label back = new Label("");
        // if save or back button is clicked
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event)
            {
              save.setText("The changes has been saved");
              back.getScene();

            }
        };


        // when button is pressed
        save_button.setOnAction(event);

        // grid pane
        GridPane user_password_grid = new GridPane();

        // size of the grid
        user_password_grid.setMinSize(400, 200);

        // the padding
        user_password_grid.setPadding(new Insets(10, 10, 10, 10));

        // vertical and horizontal gaps between the columns
        user_password_grid.setVgap(5);
        user_password_grid.setHgap(5);

        // Grid alignment
        user_password_grid.setAlignment(Pos.BASELINE_CENTER);

        //Arranging all the nodes in the grid
        user_password_grid.add(username, 0, 0);
        user_password_grid.add(Username_field , 1, 0);
        user_password_grid.add(password, 0, 1);
        user_password_grid.add(Password_field, 1, 1);
        user_password_grid.add(save_button, 0, 2);
        user_password_grid.add(back_button, 1, 2);
        user_password_grid.add(save, 1, 4);

        // scene object
        Scene scene = new Scene(user_password_grid);

        // title of the stage
        stage.setTitle("Change user: User ID");

        // scene to the stage
        stage.setScene(scene);

        // Display the stage contents
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}
