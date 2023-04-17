package main.app.models;


import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;

public class TableModel {
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
    public TableModel(int Id, String User) {
        this.Id = Id;
        this.User = User;
    }

    // constructor for checkbox, id and user
    public TableModel(String user, int id, CheckBox Select) {
        this.Select = Select;
        this.Id = id;
        this.User = user;

    }

}