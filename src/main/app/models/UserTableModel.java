package main.app.models;


import javafx.scene.control.CheckBox;

public class UserTableModel {

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

    public void setSelect(CheckBox select) {
        this.Select = select;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    // constructor for id and user
    public UserTableModel(int Id, String User) {
        this.Id = Id;
        this.User = User;
    }

   // constructor for checkbox
    public UserTableModel(int Id, String User, CheckBox Select) {
        this.Select = Select;
        this.Id = Id;
        this.User = User;
    }



    // Constructor for id, user and select.
    //public TableModel(String User, int ID, CheckBox Select) {
    //    this.ID = ID;
     //   this.User = User;
      //  this.Select = Select;

   // }


}
