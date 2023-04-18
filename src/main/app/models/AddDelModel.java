package main.app.models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddDelModel {

    private ObservableList<TableRow> addUserList;

    public class TableRow {
    int Id;
    String User;
    CheckBox Select;


    // Getter and Setter methods for id, user and select.
    public int getId() {
        return Id;
    }

    public void setID(int Id) {
        this.Id = Id;
    }

    public CheckBox getSelect() {
        return Select;
    }

    public void setSelect(CheckBox Select) {
        this.Select = Select;
    }

    public String getUser() {
        return User;
    }


    // constructor for id and user
    public TableRow(int Id, String User) {
        this.Id = Id;
        this.User = User;
    }

    // constructor for checkbox, id and user
    public TableRow(String user, int id, CheckBox Select) {
        this.Select = Select;
        this.Id = id;
        this.User = user;

    }

    }

    public AddDelModel(){
        addUserList = FXCollections.observableArrayList();
        createNewTable();

    }

    public void createNewTable() {
        try {
            String sql = "SELECT * FROM p2.users";
            Connection connect = DatabaseConnection.getConnection();

            try (Statement stmt = connect.createStatement()) {
                ResultSet result = stmt.executeQuery(sql);
                TableRow userTable;

                while (result.next()) {
                    userTable = new TableRow(result.getInt("Id"), result.getString("User"));


                    addUserList.add(userTable);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<TableRow> getAddUserList() {
        return addUserList;
    }

}


