package main.app.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.app.controllers.ConfirmationPopUpController;


import java.io.IOException;

public class ConfirmationPopUpView extends Application {
    Runnable[] methodsOnYes;
    Runnable[] methodsOnNo;

    public ConfirmationPopUpView(Runnable[] methodsOnYes, Runnable[] methodsOnNo){
        this.methodsOnYes = methodsOnYes;
        this.methodsOnNo = methodsOnNo;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(OrderPageView.class.getResource("/main/resources/confirmationPopUp.fxml"));
        Parent root = loader.load();
        ConfirmationPopUpController controller = loader.getController();
        controller.setStage(stage);
        controller.setMethodsOnYes(this.methodsOnYes);
        controller.setMethodsOnNo(this.methodsOnNo);

        Scene scene = new Scene(root);
        stage.setTitle("Confirmation");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

