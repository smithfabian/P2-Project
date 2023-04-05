package p2.AddDelUser;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class AddDelController implements Initializable {


    // define stage scene and root
    private Stage stage;
    private Scene scene;
    private Parent root;

    // the columns of the table
    @FXML
    private TableColumn<TableModel, String> User;

    @FXML
    private TableColumn<TableModel, Integer> ID;

    @FXML
    private TableColumn<TableModel, CheckBox> Select;

    @FXML
    private TableView<TableModel> tableView;

    ObservableList<TableModel> list = FXCollections.observableArrayList();

    // connect to a database that has the table of users
    // then fill each column with the info form the table in the database

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i = 0; i < 10; i++){
            CheckBox ch = new CheckBox("" + i);
            list.add(new TableModel("Peter", i, ch));
        }

        // set name for each column

        tableView.setItems(list);
        ID.setCellValueFactory(new PropertyValueFactory<TableModel, Integer>("Id"));
        User.setCellValueFactory(new PropertyValueFactory<TableModel, String>("user"));
        Select.setCellValueFactory(new PropertyValueFactory<TableModel, CheckBox>("select"));
    }

    // delete button usage
    @FXML
    private void deleteSelectedRow(ActionEvent event1) {
        for (TableModel row : tableView.getItems())
        {
            if(row.getSelect().isSelected()) {
                Platform.runLater(()-> tableView.getItems().remove(row));

            }
        }

    }

    // back button or add user button usage:
    @FXML
    private void previousScene(ActionEvent event1) throws IOException {
        root = FXMLLoader.load(getClass().getResource(""));
        stage =(Stage)((Node)event1.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void changeUserScene(ActionEvent event2) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/p2/ChangeUser/ChangeUser-view.fxml")));
        stage =(Stage)((Node)event2.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }




}
