package main.app;

import javafx.application.Application;
import javafx.stage.Stage;
import main.app.views.LoginView;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        LoginView view = new LoginView();
        view.start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}