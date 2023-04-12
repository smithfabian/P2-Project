package main.app.controllers;


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
import main.app.models.DatabaseConnection;
import main.app.models.TableModel;
import main.app.views.AdminView;
import main.app.views.ChangeUserView;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
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
    private TableColumn<TableModel, String> ID;

    @FXML
    private TableColumn<TableModel, CheckBox> Select;

    @FXML
    private TableView<TableModel> tableView;



    // Connects to a database that has the table of users
    public ObservableList<TableModel> addUserListData(){
        ObservableList<TableModel> list = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM p2.users";
            DatabaseConnection db = new DatabaseConnection();
            Connection connect = db.getConnection();

            try (Statement stmt = connect.createStatement()) {
                ResultSet result = stmt.executeQuery(sql);
                TableModel userTable;

                while (result.next()) {
                    userTable = new TableModel(result.getString("Id"), result.getString("User"));


                    list.add(userTable);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Sets name for each column
    static ObservableList<TableModel> addUserList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addUserList =  addUserListData();
        ObservableList<TableModel> list = FXCollections.observableArrayList();

        for(int i = 0; i < addUserList.size(); i++) {
            CheckBox Select = new CheckBox(" " + i);

            list.add(new TableModel(addUserList.get(i).getUser(), addUserList.get(i).getId(), Select));
        }

        ID.setCellValueFactory(new PropertyValueFactory<TableModel, String>("Id"));
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
    private void previousScene() {
        AdminView view = new AdminView();
        try {
            view.start(stage);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void changeUserScene() {
        ChangeUserView view = new ChangeUserView();
        try {
            view.start(new Stage());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
