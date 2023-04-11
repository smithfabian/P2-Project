package p2.AddDelUser;


import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;

public class TableModel {
    int Id;
    String User;
    TableColumn id;
    TableColumn user;

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

    public void setUser(String User) {
        User = User;
    }


    public void setUser(TableColumn user) {
        this.user = user;
    }

    public void setId(TableColumn id) {
        this.id = id;
    }

    // constructor for id and user
    public TableModel(int Id, String User) {
        this.Id = Id;
        this.User = User;
    }


    // constructor for checkbox, id and user
    public TableModel(TableColumn<TableModel, String> user, TableColumn<TableModel, Integer> id, CheckBox Select) {
        this.Select = Select;
        this.id = id;
        this.user = user;

    }


}
