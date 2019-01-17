package lk.ijse.dep.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterStudentController {
    public AnchorPane fxRegiStudent;

    public void clickNewStudent(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/view/registerNewStudent.fxml"));
        Scene scene = new Scene(parent);
        Stage primaryStage = (Stage) fxRegiStudent.getScene().getWindow();
        primaryStage.setScene(scene);
    }

    public void currentStudent(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/view/registerCurrentStudent.fxml"));
        Scene scene = new Scene(parent);
        Stage primaryStage = (Stage) fxRegiStudent.getScene().getWindow();
        primaryStage.setScene(scene);
    }

    public void regiHome(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/view/mainPage.fxml"));
        Scene scene = new Scene(parent);
        Stage primaryStage = (Stage) fxRegiStudent.getScene().getWindow();
        primaryStage.setScene(scene);
    }
}
