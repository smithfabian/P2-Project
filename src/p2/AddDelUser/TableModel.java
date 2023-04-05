package p2.AddDelUser;


import javafx.scene.control.CheckBox;

public class TableModel {

    int ID;
    String User;
    CheckBox Select;

    // Getter and Setter methods for id, user and select.
    public int getId() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public CheckBox getSelect() {
        return Select;
    }

    public void setSelect(CheckBox select) {
        this.Select = select;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }


    // Constructor for id, user and select.
    public TableModel(String User, int ID, CheckBox Select) {
        this.ID = ID;
        this.User = User;
        this.Select = Select;

    }


}
