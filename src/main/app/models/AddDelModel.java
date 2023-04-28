package main.app.models;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;

import java.sql.*;
import java.util.List;

public class AddDelModel {

    private ObservableList<TableRow> addUserList;

    public static class TableRow {
        SimpleIntegerProperty Id;
        SimpleStringProperty User;
        CheckBox Select;

        // Getter and Setter methods for id, user and select.
        public int getId() {
            return Id.get();
        }

        public void setId(int UserId) {
            Id.set(UserId);
        }

        public CheckBox getSelect() {
            return Select;
        }

        public void setSelect(CheckBox Select) {
            this.Select = Select;
        }

        public String getUser() {
            return User.get();
        }

        public void setUser(String user) {this.User.set(user);}

        // constructor for id, checkbox and user
        public TableRow(int Id, String User, CheckBox Select) {
            this.Id = new SimpleIntegerProperty(Id);
            this.User = new SimpleStringProperty(User);
            this.Select = new CheckBox();
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
                CheckBox select = new CheckBox();


                while (result.next()) {
                    userTable = new TableRow(result.getInt("Id"), result.getString("User"), select);

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

    public void deleteUserFromDatabase(List<String> id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String deleteQuery = "DELETE FROM p2.users WHERE Id = ?";

            try (PreparedStatement selected = conn.prepareStatement(deleteQuery)) {
                for (String ids : id) {
                    selected.setString(1, ids);
                    selected.addBatch();
                }
                selected.executeBatch();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();

        }
    }

        public ObservableList<TableRow> getAddUserList(TableColumn<TableRow, Integer> ID) {
        return addUserList;
    }

}
