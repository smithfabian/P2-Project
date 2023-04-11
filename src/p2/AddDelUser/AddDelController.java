package p2.AddDelUser;

import Model.DatabaseConnection;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;


public class AddDelController implements Initializable {


    // define stage scene and root
    private Stage stage;
    private Scene scene;
    private Parent root;

    // the columns of the table
    @FXML
    private TextField searchUser;
    @FXML
    private TableColumn<TableModel, String> User;

    @FXML
    private TableColumn<TableModel, Integer> ID;

    @FXML
    private TableColumn<TableModel, CheckBox> Select;

    @FXML
    private TableView<TableModel> tableView;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;


    // Connects to a database that has the table of users
    public ObservableList<TableModel> addUserListData(){

        ObservableList<TableModel> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM users";

        connect = DatabaseConnection.getConnection;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            TableModel userTable;

            while (result.next()) {
                userTable = new TableModel(result.getInt("Id"), result.getString("User"));
                list.add(userTable);
            }
        }catch(Exception e){e.printStackTrace();}
        return list;
    }

    // Sets name for each column
    private ObservableList<TableModel> addUserList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addUserList =  addUserListData();

        for(int i = 0; i < addUserList.size(); i++) {
            CheckBox Select = new CheckBox(" " + i);

            addUserList.add(new TableModel(User, ID, Select));
        }

        ID.setCellValueFactory(new PropertyValueFactory<TableModel, Integer>("Id"));
        User.setCellValueFactory(new PropertyValueFactory<TableModel, String>("User"));
        Select.setCellValueFactory(new PropertyValueFactory<TableModel, CheckBox>("Select"));
        tableView.setItems(addUserList);
    }

    //search for a user in the table (name based) method
    @FXML
    public void UserListSearch() {

        FilteredList<TableModel> filter = new FilteredList<>(addUserList, e -> true);
        searchUser.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicateTableModel -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateTableModel.getUser().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<TableModel> sortedData = new SortedList<>(filter);

        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(filter);

}


    // delete button usage
    //select user through the checkbox and delete the selected users by pressing the delete button.
    // TODO
    @FXML
    private void deleteSelectedRow() {
        for (TableModel addUserList : tableView.getItems())
        {
            if(addUserList.getSelect().isSelected()) {
                Platform.runLater(()-> tableView.getItems().remove(addUserList));

                // Also delete in database table
                //TODO

                }

            }

        }


    // back button or add user button usage:
    //goes back to admin page
    //TODO
    @FXML
    private void previousScene(ActionEvent event2) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/p2/AdminPage/adminPage.fxml"));
        stage =(Stage)((Node)event2.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void changeUserScene(ActionEvent event3) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/p2/ChangeUser/ChangeUser-view.fxml")));
        stage =(Stage)((Node)event3.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
