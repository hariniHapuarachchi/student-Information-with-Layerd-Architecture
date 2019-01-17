package lk.ijse.dep.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.business.BOFactory;
import lk.ijse.dep.business.custom.ManageBatchesBO;
import lk.ijse.dep.business.custom.ManageCoursesBO;
import lk.ijse.dep.business.custom.ManageStudentsBO;
import lk.ijse.dep.dto.*;
import lk.ijse.dep.view.util.BatchDetTM;
import lk.ijse.dep.view.util.StudentWithBatchTM;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageCurrentStudentController {
    public AnchorPane fxCurrentSt;
    public TextField currentSID;
    public TextField currentCrs;
    public TextField currentBID;
    public TableView<StudentWithBatchTM> currentStudentTab;

    private ManageStudentsBO manageStudentsBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_STUDENTS);
    private ManageCoursesBO manageCoursesBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_COURSES);
    private ManageBatchesBO manageBatchesBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_BATCHES);

    public void initialize(){

        currentStudentTab.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("student"));
        currentStudentTab.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("batch"));

        List<StudentWithBatchDTO> stWithBatchDB = null;
        try {
            stWithBatchDB = manageStudentsBO.getStudentsWithBatches();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        ObservableList<StudentWithBatchDTO> studentWithBatchDTOS = FXCollections.observableArrayList(stWithBatchDB);
        ObservableList<StudentWithBatchTM> tblItems = FXCollections.observableArrayList();

        for (StudentWithBatchDTO studentWithBatchDTO : studentWithBatchDTOS) {
            tblItems.add(new StudentWithBatchTM(studentWithBatchDTO.getBatch(),studentWithBatchDTO.getStudent()));
        }


        currentStudentTab.setItems(tblItems);

    }
    public void saveCurrentCrs(ActionEvent actionEvent) throws IOException {

        if (currentSID.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Student ID is empty", ButtonType.OK).showAndWait();
            currentSID.requestFocus();
            return;
        } else if (currentBID.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Batch ID is empty", ButtonType.OK).showAndWait();
            currentBID.requestFocus();
            return;
        } else if (currentCrs.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Course is empty", ButtonType.OK).showAndWait();
            currentCrs.requestFocus();
            return;
        }

            String studentID = currentSID.getText();

            StudentDetDTO studentDetDTO = null;
            try {
                studentDetDTO = manageStudentsBO.findStudents(studentID);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }

            if (studentDetDTO == null) {
                new Alert(Alert.AlertType.ERROR, "Invalid Student ID", ButtonType.OK).showAndWait();
                currentSID.requestFocus();
                return;
            }

            CourseDetDTO courseDetDTO = null;
            try {
                courseDetDTO = manageCoursesBO.findCourse(currentCrs.getText());
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }

            if (courseDetDTO == null) {
                new Alert(Alert.AlertType.ERROR, "Invalid Course ID", ButtonType.OK).showAndWait();
                currentCrs.requestFocus();
                return;
            }

            BatchDetDTO batchDetDTO = null;
            try {
                batchDetDTO = manageBatchesBO.findBatch(currentBID.getText());
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }

//            if (batchDetDTO == null) {
//                new Alert(Alert.AlertType.ERROR, "Invalid Batch ID", ButtonType.OK).showAndWait();
//                currentBID.requestFocus();
//                return;
//            }

            StudentWithBatchTM studentWithBatchTM = new StudentWithBatchTM(currentBID.getText(), currentSID.getText());
            currentStudentTab.getItems().add(studentWithBatchTM);
            StudentWithBatchDTO studentWithBatchDTO = new StudentWithBatchDTO(currentBID.getText(), currentSID.getText());
            boolean result = false;
            try {
                result = manageStudentsBO.createStudentWithBatch(studentWithBatchDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }

            currentCrs.clear();
            currentBID.clear();
            currentSID.clear();

            currentSID.setEditable(true);
            currentCrs.setEditable(true);
            currentBID.setEditable(true);


    }

    public void createCrrntNewCrs(ActionEvent actionEvent) {

        currentCrs.clear();
        currentBID.clear();
        currentSID.clear();

        currentSID.setEditable(true);
        currentCrs.setEditable(true);
        currentBID.setEditable(true);
    }

    public void CurrentStudentHome(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/view/mainPage.fxml"));
        Scene scene = new Scene(parent);
        Stage primaryStage = (Stage) fxCurrentSt.getScene().getWindow();
        primaryStage.setScene(scene);
    }
}
