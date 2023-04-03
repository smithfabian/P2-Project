package p2.add_del_user;


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
import java.util.ResourceBundle;

public class Add_del_Controller implements Initializable {


    // define stage scene and root
    private Stage stage;
    private Scene scene;
    private Parent root;

    // the columns of the table
    @FXML
    private TableColumn<TableSetterGetter, String> User;

    @FXML
    private TableColumn<TableSetterGetter, Integer> ID;

    @FXML
    private TableColumn<TableSetterGetter, CheckBox> Select;

    @FXML
    private TableView<TableSetterGetter> tableView;

    ObservableList<TableSetterGetter> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i = 0; i < 10; i++){
            CheckBox ch = new CheckBox("" + i);
            list.add(new TableSetterGetter("Peter", i, ch));
        }

        tableView.setItems(list);
        ID.setCellValueFactory(new PropertyValueFactory<TableSetterGetter, Integer>("Id"));
        User.setCellValueFactory(new PropertyValueFactory<TableSetterGetter, String>("user"));
        Select.setCellValueFactory(new PropertyValueFactory<TableSetterGetter, CheckBox>("select"));
    }
    @FXML
    private void deleteSelectedRow(ActionEvent event1) {
        for (TableSetterGetter row : tableView.getItems())
        {
            if(row.getSelect().isSelected()) {
                Platform.runLater(()-> {
                    tableView.getItems().remove(row);

                });

            }
        }

    }

    @FXML
    private void previousScene(ActionEvent event1) throws IOException {
        root = FXMLLoader.load(getClass().getResource("insert scene here"));
        stage =(Stage)((Node)event1.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void changeUserScene(ActionEvent event2) throws IOException {
        root = FXMLLoader.load(getClass().getResource("insert scene here"));
        stage =(Stage)((Node)event2.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
