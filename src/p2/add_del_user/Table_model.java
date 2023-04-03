package p2.add_del_user;


import javafx.scene.control.CheckBox;

public class Table_model {

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
    public Table_model(String User, int ID, CheckBox Select) {
        this.ID = ID;
        this.User = User;
        this.Select = Select;

    }


}
