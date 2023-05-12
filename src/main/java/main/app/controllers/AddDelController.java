package main.app.controllers;


import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.app.models.AddDelModel;
import main.app.models.Session;
import main.app.views.MainPageView;
import main.app.views.ChangeUserView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AddDelController {
    private static final Logger logger = LogManager.getLogger(AddDelController.class.getName());

    // Define stage scene and root
    private Stage stage;

    // The columns of the table
    @FXML
    private TextField searchUser;
    @FXML
    private TableColumn<AddDelModel.TableRow, String> User;

    @FXML
    private TableColumn<AddDelModel.TableRow, Integer> ID;

    @FXML
    private TableColumn<AddDelModel.TableRow, CheckBox> Select;

    @FXML
    private TableView<AddDelModel.TableRow> tableView;

    @FXML
    private TableView UserTable;
    private AddDelModel addDelModel;


    public void initialize() {

        ID.setCellValueFactory(new PropertyValueFactory<AddDelModel.TableRow, Integer>("Id"));
        User.setCellValueFactory(new PropertyValueFactory<AddDelModel.TableRow, String>("User"));
        Select.setCellValueFactory(new PropertyValueFactory<AddDelModel.TableRow, CheckBox>("Select"));
        updateTable();

    }

    public void updateTable() {
        addDelModel = new AddDelModel();
        tableView.setItems(addDelModel.getAddUserList(ID));

    }

    //Search for a user in the table (name based) method
    @FXML
    public void UserListSearch() {

        FilteredList<AddDelModel.TableRow> filter = new FilteredList<>(addDelModel.getAddUserList(ID), e -> true);
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

        SortedList<AddDelModel.TableRow> sortedData = new SortedList<>(filter);

        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        tableView.setItems(filter);

    }
    // Delete button usage
    //select user through the checkbox and delete the selected users by pressing the delete button.
    @FXML
    private void deleteSelectedRow() {

        List<String> idArray = new ArrayList<>();

        for (AddDelModel.TableRow addUserList : tableView.getItems())
        {
            if(addUserList.getSelect().isSelected()) {
                idArray.add(String.valueOf(addUserList.getId()));

                Platform.runLater(()-> tableView.getItems().remove(addUserList));
            }

        }
        if (Session.getLoggedInUser() == 30) {
            logger.info("Usability test deleted user");
        }
        addDelModel.deleteUserFromDatabase(idArray);
    }

    //Back button or add user button usage:
    @FXML
    private void previousScene() {
        MainPageView view = new MainPageView();
        try {
            view.start(stage);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Add user button will take you to the changeUser scene
    @FXML
    private void changeUserScene() {
        if (Session.getLoggedInUser() == 30) {
            logger.info("Usability test clicked on add user button");
        }
        ChangeUserView view = new ChangeUserView(this);
        try {
            view.start(new Stage());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    //selected user - set userId to the user being selected
    //write the newly changed user to database
    //TODO
    public void UserRowClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {

           
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}