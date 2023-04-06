package main.app;

import javafx.application.Application;
import javafx.stage.Stage;
import main.app.views.LoginView;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        LoginView view = new LoginView();
        view.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
