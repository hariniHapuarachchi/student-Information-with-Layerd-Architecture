package lk.ijse.dep.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {
    public ImageView student;
    public ImageView course;
    public ImageView batch;
    public ImageView mSt;

    public void clickRegistration(MouseEvent mouseEvent) throws IOException {

        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/view/registerStudent.fxml"));
        Scene scene = new Scene(parent);
        Stage primaryStage = (Stage) student.getScene().getWindow();
        primaryStage.setScene(scene);
    }

    public void clickManageCourse(MouseEvent mouseEvent) throws IOException {

        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/view/manageCourse.fxml"));
        Scene scene = new Scene(parent);
        Stage primaryStage = (Stage) course.getScene().getWindow();
        primaryStage.setScene(scene);
    }

    public void clickManageBatch(MouseEvent mouseEvent) throws IOException {

        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/view/manageBatch.fxml"));
        Scene scene = new Scene(parent);
        Stage primaryStage = (Stage) batch.getScene().getWindow();
        primaryStage.setScene(scene);
    }

    public void clickManageStudent(MouseEvent mouseEvent) throws IOException {

        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/view/manageStudent.fxml"));
        Scene scene = new Scene(parent);
        Stage primaryStage = (Stage) mSt.getScene().getWindow();
        primaryStage.setScene(scene);
    }
}
