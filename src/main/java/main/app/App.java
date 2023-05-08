package main.app;

import javafx.application.Application;
import javafx.stage.Stage;
import main.app.models.DatabaseConnection;
import main.app.views.LoginView;

import java.sql.Connection;
import java.sql.SQLException;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            conn.close();
        } catch (SQLException e) {
            // TODO: show error message to user saying app could not connect to database
        }
        LoginView view = new LoginView();
        view.start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
