package main.app.controllers;

import main.app.models.DatabaseConnection;
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
import main.app.models.UserTableModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;


public class UserController implements Initializable {


    // define stage scene and root
    private Stage stage;
    private Scene scene;
    private Parent root;

    // the columns of the table
    @FXML
    private TableColumn<UserTableModel, String> User;

    @FXML
    private TableColumn<UserTableModel, Integer> ID;

    @FXML
    private TableColumn<UserTableModel, CheckBox> Select;

    @FXML
    private TableView<UserTableModel> tableView;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    private ActionEvent event1;


    // Connect to a database that has the table of users
    public ObservableList<UserTableModel> addUserListData(){
        ObservableList<UserTableModel> list = FXCollections.observableArrayList();

        String sql = "SELECT * FROM users";

        connect = DatabaseConnection.getConnection;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            UserTableModel userTable;

            while(result.next()) {
                userTable = new UserTableModel(result.getInt("Id"), result.getString("User"));
                list.add(userTable);
            }
        }catch(Exception e){e.printStackTrace();}
        return list;
    }

    // Set name for each column
    private ObservableList<UserTableModel> addUserList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addUserList =  addUserListData();

        ID.setCellValueFactory(new PropertyValueFactory<UserTableModel, Integer>("Id"));
        User.setCellValueFactory(new PropertyValueFactory<UserTableModel, String>("user"));
        Select.setCellValueFactory(new PropertyValueFactory<UserTableModel, CheckBox>("select"));
        tableView.setItems(addUserList);
    }

    // delete button usage  - also delete from the table in the database method
    //select user through the checkbox and delete the selected users by pressing the delete button.
    // TO DO
    @FXML
    private void deleteSelectedRow(ActionEvent event1) {
        for (UserTableModel row : tableView.getItems())
        {
            if(row.getSelect().isSelected()) {
                Platform.runLater(()-> tableView.getItems().remove(row));

                UserTableModel userTable = tableView.getSelectionModel().getSelectedItem();

                }

            }

        }



    //search for a user in the table (name based) method
    // TO DO



    // back button or add user button usage:
    //DOESNT WORK
    @FXML
    private void previousScene(ActionEvent event2) throws IOException {
        root = FXMLLoader.load(getClass().getResource(""));
        stage =(Stage)((Node)event2.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void changeUserScene(ActionEvent event3) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/main/resources/ChangeUser-view.fxml")));
        stage =(Stage)((Node)event3.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
