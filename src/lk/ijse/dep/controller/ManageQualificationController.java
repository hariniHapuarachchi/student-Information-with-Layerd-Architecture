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
import lk.ijse.dep.business.custom.ManageStudentsBO;
import lk.ijse.dep.db.DBConnection;
import lk.ijse.dep.dto.CourseDetDTO;
import lk.ijse.dep.dto.StudentQualificationDTO;
import lk.ijse.dep.view.util.CourseDetTM;
import lk.ijse.dep.view.util.StudentQualifiationTM;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageQualificationController {

    @FXML
    private Button btnDelete;
    @FXML
    private Button btnSave;
    @FXML
    private TextField qStudentID;
    @FXML
    private AnchorPane fxStQual;
    @FXML
    private TableView<StudentQualifiationTM> qualTable;
    @FXML
    private TableColumn SID;
    @FXML
    private TableColumn ql;
    @FXML
    private TableColumn ins;
    @FXML
    private TableColumn dt;
    @FXML
    private TableColumn spcl;
    @FXML
    private TextField qID;
    @FXML
    private TextField qQual;
    @FXML
    private TextField qIns;
    @FXML
    private TextField qDate;
    @FXML
    private TextField qspc;


    Connection conn = null;
    private ManageStudentsBO manageStudentsBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_STUDENTS);


    public void initialize() throws Exception {

        qStudentID.setText(ManageNewStudentController.ID);
//        qID.setText(manageStudentsBO.generateQualificationId());
//        qStudentID.setEditable(false);
//        qID.setEditable(false);

        qualTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("qualID"));
        qualTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("qualification"));
        qualTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("institute"));
        qualTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("awardDate"));
        qualTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("specialization"));

//        btnSave.setDisable(true);
//        btnDelete.setDisable(true);

        List<StudentQualificationDTO> studentQualDB = null;
        try {
            studentQualDB = manageStudentsBO.getQualification();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        ObservableList<StudentQualificationDTO> studentQualificationDTOS = FXCollections.observableArrayList(studentQualDB);
        ObservableList<StudentQualifiationTM> tblItems = FXCollections.observableArrayList();
        for (StudentQualificationDTO studentQualificationDTO : studentQualificationDTOS) {
            tblItems.add(new StudentQualifiationTM(studentQualificationDTO.getQualID(),studentQualificationDTO.getQualification(),studentQualificationDTO.getInstitute(),
                    studentQualificationDTO.getAwardDate(),studentQualificationDTO.getSpecialization()));
        }
        qualTable.setItems(tblItems);

        qualTable.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<StudentQualifiationTM>() {
                    @Override

                    public void changed(ObservableValue<? extends StudentQualifiationTM> ov, StudentQualifiationTM old_val, StudentQualifiationTM new_val) {

                        if (new_val == null) {
                            return;
                        }

                        qStudentID.setEditable(false);
                        qID.setEditable(false);

                        qID.setText(new_val.getQualID());
                        qQual.setText(new_val.getQualification());
                        qIns.setText(new_val.getInstitute());
                        qDate.setText(new_val.getAwardDate());
                        qspc.setText(new_val.getSpecialization());

                        btnSave.setDisable(false);
                        btnDelete.setDisable(false);
                    }
                });
    }

    public void saveQualification(ActionEvent actionEvent) throws IOException, SQLException {

        if (qStudentID.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Can't Save a Qualifications without a Student Id", ButtonType.OK).showAndWait();
            qStudentID.requestFocus();
            return;
        }

        ObservableList<StudentQualifiationTM> items = qualTable.getItems();
        ArrayList<StudentQualificationDTO> studentQualificationDTOS = new ArrayList<>();

        for (StudentQualifiationTM item : items) {
            studentQualificationDTOS.add(new StudentQualificationDTO(item.getQualID(), item.getQualification(), item.getInstitute(), item.getAwardDate(),item.getSpecialization()));
        }

        try {
//            conn = DBConnection.getConnection();
//            conn.setAutoCommit(false);
           manageStudentsBO.CreateQualification(new StudentQualificationDTO(qStudentID.getText(), studentQualificationDTOS));


           // DBConnection.getConnection().commit();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
//            if (DBConnection.getConnection() != null){
//                try{
//                    DBConnection.getConnection().rollback();
//                }catch (SQLException ex){
//                    ex.printStackTrace();
//                }
//            }
        }

//        new Alert(Alert.AlertType.CONFIRMATION, "Qualification has been Saved successfully", ButtonType.OK).showAndWait();
//        hardReset();

        Parent pt = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/view/manageParentDet.fxml"));
        Scene scene = new Scene(pt);
        Stage primaryStage = (Stage) fxStQual.getScene().getWindow();
        primaryStage.setScene(scene);


    }

    public void qualAdd(ActionEvent actionEvent) {

        if (qID.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Qualification ID is empty", ButtonType.OK).showAndWait();
            qID.requestFocus();
            return;
        } else if (qQual.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Qualification is empty", ButtonType.OK).showAndWait();
            qQual.requestFocus();
            return;
        } else if (qIns.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Institute is empty", ButtonType.OK).showAndWait();
            qIns.requestFocus();
            return;
        }else if (qDate.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Award Date is empty", ButtonType.OK).showAndWait();
            qDate.requestFocus();
            return;
        }

        if (!isValidDate(qDate.getText())){
            new Alert(Alert.AlertType.ERROR, "Invalid Date Format", ButtonType.OK).show();
            return;
        }

        if (qualTable.getSelectionModel().isEmpty()) {

            StudentQualifiationTM studentQualifiationTM = null;

            ObservableList<StudentQualifiationTM> items = qualTable.getItems();
            for (StudentQualifiationTM studentQualifiationTM1 : items) {
                if (studentQualifiationTM1.getQualID().equals(qID.getText())) {
                    new Alert(Alert.AlertType.ERROR, "Duplicate Qualification IDs are not allowed").showAndWait();
                    qID.requestFocus();
                    return;
                }
            }



                if ((studentQualifiationTM = isQualificationExist(qID.getText())) == null) {


                        StudentQualifiationTM studentQualifiationTM2 = new StudentQualifiationTM(qID.getText(),qQual.getText(), qIns.getText(), qDate.getText(),qspc.getText());
                        qualTable.getItems().add(studentQualifiationTM2);



                }


//             //StudentQualificationDTO studentQualificationDTO = new StudentQualificationDTO(qID.getText(), qQual.getText(), qIns.getText(),qDate.getText(),qspc.getText());
//            boolean result = false;
//            try {
//                result = manageStudentsBO.CreateQualification(studentQualificationDTO);
//            } catch (Exception e) {
//                Logger.getLogger("").log(Level.SEVERE, null, e);
//            }
//
//            if (result) {
//                new Alert(Alert.AlertType.INFORMATION, "Course has been saved successfully", ButtonType.OK).showAndWait();
//                qualTable.scrollTo(courseDetTM);
//            } else {
//                new Alert(Alert.AlertType.ERROR, "Failed to save the Course", ButtonType.OK).showAndWait();
//            }

        } else {



            StudentQualifiationTM selectedQual = qualTable.getSelectionModel().getSelectedItem();
            selectedQual.setQualification(qQual.getText());
            selectedQual.setInstitute(qIns.getText());
            selectedQual.setAwardDate(qDate.getText());
            selectedQual.setSpecialization(qspc.getText());
            qualTable.refresh();

        }

        resest();
    }

    public void newQualification(ActionEvent actionEvent) throws Exception {

        resest();
    }

    public void qualHome(ActionEvent actionEvent) throws IOException {

        Parent pt = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/view/mainPage.fxml"));
        Scene scene = new Scene(pt);
        Stage primaryStage = (Stage) fxStQual.getScene().getWindow();
        primaryStage.setScene(scene);
    }

    public boolean isValidDate(String adate){

        return adate.matches("^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$");

    }

    public void qualDelete(ActionEvent actionEvent) {

        StudentQualifiationTM selectedItem = qualTable.getSelectionModel().getSelectedItem();
        qualTable.getItems().remove(selectedItem);

        resest();

    }

    public void resest(){

        qStudentID.setEditable(false);
        qID.clear();
        qQual.clear();
        qIns.clear();
        qDate.clear();
        qspc.clear();

        qID.setEditable(true);
        qQual.setEditable(true);
        qIns.setEditable(true);
        qDate.setEditable(true);
        qspc.setEditable(true);
    }

    private void hardReset() {
        resest();
        qualTable.getItems().removeAll(qualTable.getItems());

        try {
            qID.setText(manageStudentsBO.generateQualificationId());
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

        qQual.requestFocus();
    }

    private StudentQualifiationTM isQualificationExist(String QulID) {
        ObservableList<StudentQualifiationTM> items = qualTable.getItems();
        for (StudentQualifiationTM item : items) {
            if (item.getQualID().equals(QulID)) {
                return item;
            }
        }
        return null;
    }
}
