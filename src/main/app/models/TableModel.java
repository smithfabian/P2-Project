package main.app.models;


import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;

public class TableModel {
    String Id;
    String User;
    CheckBox Select;


    // Getter and Setter methods for id, user and select.
    public String getId() {
        return Id;
    }
    public void setID(String Id) {
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

    public void setUser(String User) {
        User = User;
    }

    // constructor for id and user
    public TableModel(String Id, String User) {
        this.Id = Id;
        this.User = User;
    }


    // constructor for checkbox, id and user
    public TableModel(String user, String id, CheckBox Select) {
        this.Select = Select;
        this.Id = id;
        this.User = user;

    }


}
