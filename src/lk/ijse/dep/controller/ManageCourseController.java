package lk.ijse.dep.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.business.BOFactory;
import lk.ijse.dep.business.custom.ManageBatchesBO;
import lk.ijse.dep.business.custom.ManageCoursesBO;
import lk.ijse.dep.dto.BatchDetDTO;
import lk.ijse.dep.dto.CourseDetDTO;
import lk.ijse.dep.view.util.BatchDetTM;
import lk.ijse.dep.view.util.CourseDetTM;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageCourseController {

    public Button btnSave;
    public Button btnDelete;
    @FXML
    private AnchorPane fxCorse;
    @FXML
    private TextField courseId;
    @FXML
    private TextField courseName;
    @FXML
    private TextField courseDes;
    @FXML
    private TextField courseDur;
    @FXML
    private TableView<CourseDetTM> courseTable;

    private ManageCoursesBO manageCoursesBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_COURSES);
    public void initialize(){

        courseTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("cid"));
        courseTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("cname"));
        courseTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
        courseTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("duration"));

        btnSave.setDisable(true);
        btnDelete.setDisable(true);

        List<CourseDetDTO> coursDB = null;
        try {
            coursDB = manageCoursesBO.getCourses();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        ObservableList<CourseDetDTO> courseDetDTOS = FXCollections.observableArrayList(coursDB);
        ObservableList<CourseDetTM> tblItems = FXCollections.observableArrayList();
        for (CourseDetDTO courseDetDTO : courseDetDTOS) {
            tblItems.add(new CourseDetTM(courseDetDTO.getCid(),courseDetDTO.getCname(),courseDetDTO.getDescription(),courseDetDTO.getDuration()));
        }
        courseTable.setItems(tblItems);

        courseTable.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<CourseDetTM>() {
                    @Override

                    public void changed(ObservableValue<? extends CourseDetTM> ov, CourseDetTM old_val, CourseDetTM new_val) {

                        if (new_val == null) {
                            return;
                        }

                        courseId.setEditable(false);

                        courseId.setText(new_val.getCid());
                        courseName.setText(new_val.getCname());
                        courseDes.setText(new_val.getDescription());
                        courseDur.setText(new_val.getDuration());

                        btnSave.setDisable(false);
                        btnDelete.setDisable(false);
                    }
                });
    }
    public void saveCourse(ActionEvent actionEvent) {

        if (courseId.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Course ID is empty", ButtonType.OK).showAndWait();
            courseId.requestFocus();
            return;
        } else if (courseName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Course Name is empty", ButtonType.OK).showAndWait();
            courseName.requestFocus();
            return;
        } else if (courseDes.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Description is empty", ButtonType.OK).showAndWait();
            courseDes.requestFocus();
            return;
        }else if (courseDur.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Duration is empty", ButtonType.OK).showAndWait();
            courseDur.requestFocus();
            return;
        }


        if (courseTable.getSelectionModel().isEmpty()) {

            ObservableList<CourseDetTM> items = courseTable.getItems();
            for (CourseDetTM courseDetTM : items) {
                if (courseDetTM.getCid().equals(courseId.getText())) {
                    new Alert(Alert.AlertType.ERROR, "Duplicate Course IDs are not allowed").showAndWait();
                    courseId.requestFocus();
                    return;
                }
            }

            CourseDetTM courseDetTM = new CourseDetTM(courseId.getText(),courseName.getText(), courseDes.getText(), courseDur.getText());
            courseTable.getItems().add(courseDetTM);
            CourseDetDTO courseDetDTO = new CourseDetDTO(courseId.getText(), courseName.getText(), courseDes.getText(),courseDur.getText());
            boolean result = false;
            try {
                result = manageCoursesBO.CreateCourse(courseDetDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }

            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Course has been saved successfully", ButtonType.OK).showAndWait();
                courseTable.scrollTo(courseDetTM);
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save the Course", ButtonType.OK).showAndWait();
            }

        } else {

            CourseDetTM selectedCourse = courseTable.getSelectionModel().getSelectedItem();
            selectedCourse.setCid(courseId.getText());
            selectedCourse.setCname(courseName.getText());
            selectedCourse.setDescription(courseDes.getText());
            selectedCourse.setDuration(courseDur.getText());
            courseTable.refresh();

            String selectedCourseID = courseTable.getSelectionModel().getSelectedItem().getCid();

            boolean result = false;
            try {
                result = manageCoursesBO.updateCourse(new CourseDetDTO(courseId.getText(),courseName.getText(),courseDes.getText(),courseDur.getText()));
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }

            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Course has been updated successfully", ButtonType.OK).showAndWait();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update the Course", ButtonType.OK).showAndWait();
            }
        }

        resest();

    }

    public void deleteCourse(ActionEvent actionEvent) {

        Alert confirmMsg = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this Course?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = confirmMsg.showAndWait();

        if (buttonType.get() == ButtonType.YES) {
            String selectedCourse = courseTable.getSelectionModel().getSelectedItem().getCid();

            courseTable.getItems().remove(courseTable.getSelectionModel().getSelectedItem());
            boolean result = false;
            try {
                result = manageCoursesBO.deleteCourse(selectedCourse);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
            if (!result){
                new Alert(Alert.AlertType.ERROR, "Failed to delete the Course", ButtonType.OK).showAndWait();
            }else{
                resest();
            }
        }
    }

    public void cNewCourse(ActionEvent actionEvent) {
        resest();
    }

    public void courseHome(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/view/mainPage.fxml"));
        Scene scene = new Scene(parent);
        Stage primaryStage = (Stage) fxCorse.getScene().getWindow();
        primaryStage.setScene(scene);
    }

    public void resest(){

        courseId.setEditable(true);
        courseName.setEditable(true);
        courseDes.setEditable(true);
        courseDur.setEditable(true);
        
        courseId.clear();
        courseName.clear();
        courseDes.clear();
        courseDur.clear();

        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        courseTable.getSelectionModel().clearSelection();
    }
}
