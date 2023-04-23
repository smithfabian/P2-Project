package main.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ConfirmationPopUpController {
    Stage stage;
    Runnable[] MethodsOnYes;
    Runnable[] MethodsOnNo;

    @FXML
    Button yesButton;
    @FXML
    Button noButton;
    @FXML
    Text message;


    public void setStage(Stage stage){
        this.stage = stage;
    }


    public void yesButtonClicked(){
        for (Runnable method : MethodsOnYes){
            method.run();
        }
        stage.close();
    }

    public void noButtonClicked(){
        for (Runnable method : MethodsOnNo){
            method.run();
        }
        stage.close();
    }

    public void setMethodsOnYes(Runnable[] MethodsOnYes) {
        this.MethodsOnYes = MethodsOnYes;
    }

    public void setMethodsOnNo(Runnable[] MethodsOnNo) {
        this.MethodsOnNo = MethodsOnNo;
    }
}
