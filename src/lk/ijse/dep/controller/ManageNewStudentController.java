package lk.ijse.dep.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.business.BOFactory;
import lk.ijse.dep.business.custom.ManageStudentsBO;
import lk.ijse.dep.db.DBConnection;
import lk.ijse.dep.dto.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageNewStudentController {

    @FXML
    private TextField studentCourse;
    @FXML
    private TextField studentBatch;
    @FXML
    private AnchorPane fxStudentInfo;
    @FXML
    private TextField sInitials;
    @FXML
    private TextField sName;
    @FXML
    private TextField sAddress;
    @FXML
    private TextField sTeleHome;
    @FXML
    private TextField sTeleMob;
    @FXML
    private TextField sEmail;
    @FXML
    private TextField sDOB;
    @FXML
    private TextField sSchool;
    @FXML
    private TextField sUni;
    @FXML
    private TextField sFaculty;
    @FXML
    private RadioButton sFemale;
    @FXML
    private ToggleGroup selectGender;
    @FXML
    private RadioButton sMale;
    @FXML
    private CheckBox sMaster;
    @FXML
    private CheckBox sDegree;
    @FXML
    private CheckBox sDiploma;
    @FXML
    private CheckBox sAL;
    @FXML
    private CheckBox sOL;
    @FXML
    private TextField studentID;
    @FXML
    private TextField stnic;
    @FXML
    private TextField stCity;

    Connection conn = null;

    private int count = 1;

    private ManageStudentsBO manageStudentsBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_STUDENTS);

    public static String ID;

    public void initialize() throws Exception {

        setOrder();
        studentID.setEditable(false);

    }


    public void studentSaveDet(ActionEvent actionEvent) throws Exception {

        studentID.setEditable(false);

        if (!isNICValid(stnic.getText())){
            new Alert(Alert.AlertType.ERROR, "Invalid NIC", ButtonType.OK).show();
            return;
        }else if (stnic.getText().length() != 10){
            new Alert(Alert.AlertType.ERROR, "NIC should have 10 elements with 'v'", ButtonType.OK).show();
            return;
        }

        if (!isValidPhone(sTeleHome.getText()) || !isValidPhone(sTeleMob.getText())){
            new Alert(Alert.AlertType.ERROR, "Invalid Telephone Number", ButtonType.OK).show();
            return;
        }else if (!isValidPhone(sTeleHome.getText()) && !isValidPhone(sTeleMob.getText())){
            new Alert(Alert.AlertType.ERROR, "Both numbers are invalid", ButtonType.OK).show();
            return;
        }else if (sTeleHome.getText().length() != 11 || sTeleMob.getText().length() != 11){
            new Alert(Alert.AlertType.ERROR, "Telephone number should have 11 elements with '-'", ButtonType.OK).show();
            return;
        }else if (sTeleHome.getText().length() != 11 && sTeleMob.getText().length() != 11){
            new Alert(Alert.AlertType.ERROR, "Both numbers should have 11 elements with '-'", ButtonType.OK).show();
            return;
        }

        if (!isValidEmail(sEmail.getText())){
            new Alert(Alert.AlertType.ERROR, "Invalid Email", ButtonType.OK).show();
            return;
        }
        
        if (!isValidDOB(sDOB.getText())){
            new Alert(Alert.AlertType.ERROR, "Invalid Date Format", ButtonType.OK).show();
            return;
        }

        String id = studentID.getText();
        String nameini = sInitials.getText();
        String fName = sName.getText();
        String nicNum = stnic.getText();
        String sadd = sAddress.getText();
        String th = sTeleHome.getText();
        String tm = sTeleMob.getText();
        String eml = sEmail.getText();
        String db = sDOB.getText();
        String gen = selectedGender();
        String sch = sSchool.getText();
        String uni = sUni.getText();
        String fac = sFaculty.getText();
        String edu = selectedEdu();
        String ct = stCity.getText();
        String crs = studentCourse.getText();
        String btch = studentBatch.getText();

        StudentDetDTO studentDetDTO = null;
        try {
            studentDetDTO = manageStudentsBO.findStudents(id);
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);
        }

        if (studentDetDTO != null) {
            new Alert(Alert.AlertType.ERROR, "Student ID cannot duplicate", ButtonType.OK).showAndWait();
            studentID.requestFocus();
            return;
        }

        StudentDetDTO studentDetDTO1 = new StudentDetDTO(id,nameini,fName,nicNum,sadd,th,tm,eml,db,gen,sch,uni,fac,edu,ct);
        StudentWithBatchDTO studentWithBatchDTO = new StudentWithBatchDTO(btch,id);

        boolean result = false;

        try {

            manageStudentsBO.CreateStudents(studentDetDTO1);
            //BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            // DBConnection.getConnection().commit();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE, null, e);


        }

//        if (result) {
//            //new Alert(Alert.AlertType.INFORMATION, "Student has been saved successfully", ButtonType.OK).showAndWait();
////            boolean rslt = false;
////            try {
////                DBConnection.getConnection().setAutoCommit(false);
////                rslt = manageStudentsBO.createStudentWithBatch(studentWithBatchDTO);
////                DBConnection.getConnection().commit();
////            } catch (Exception e) {
////                Logger.getLogger("").log(Level.SEVERE, null, e);
////                if (DBConnection.getConnection() != null){
////                    try{
////                        DBConnection.getConnection().rollback();
////                    }catch (SQLException ex){
////                        ex.printStackTrace();
////                    }
////                }
////            }
////
////            if (rslt){
//
////            }else {
////                new Alert(Alert.AlertType.ERROR, "Failed to save the Course And Batch", ButtonType.OK).showAndWait();
////            }
//
//
//        } else {
//            new Alert(Alert.AlertType.ERROR, "Failed to save the Student", ButtonType.OK).showAndWait();
//        }

        ID = id;
        Parent pt = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/view/manageStudentQualification.fxml"));
        Scene scene = new Scene(pt);
        Stage primaryStage = (Stage) fxStudentInfo.getScene().getWindow();
        primaryStage.setScene(scene);

        setOrder();



    }

    public void studentHome(ActionEvent actionEvent) throws IOException {

        Parent pt = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/view/mainPage.fxml"));
        Scene scene = new Scene(pt);
        Stage primaryStage = (Stage) fxStudentInfo.getScene().getWindow();
        primaryStage.setScene(scene);
    }

    public String selectedGender(){

        String gen = null;

        if (sFemale.isSelected() == true){
             gen = sFemale.getText();
            return gen;
        }else if (sMale.isSelected()==true) {

             gen = sMale.getText();
            return gen;
        }else {
            new Alert(Alert.AlertType.ERROR, "Please select the gender", ButtonType.OK).show();
            return null;
        }



    }

    public boolean isValidPhone(String tele){
        return tele.matches("\\d{3}-\\d{7}");
    }

    public boolean isValidDOB(String dateBirth){

        return dateBirth.matches("^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$");

    }

    public String selectedEdu(){

        String ed = null;

        if (sMaster.isSelected()==true){
           //return sMaster.getText();
            ed = sMaster.getText()+"/";
        }

        if (sDegree.isSelected()==true){
            //return sDegree.getText();
            ed = sDegree.getText()+"/";
        }

        if (sDiploma.isSelected()==true){
            //return sDiploma.getText();
            ed = sDiploma.getText()+"/";
        }

        if (sAL.isSelected()==true){
            //sAL.getText();
            ed = sAL.getText()+"/";
        }

        if (sOL.isSelected()==true){
           // edunew.add("O/L");
            ed = sOL.getText()+"/";
        }
        
        return ed;
       // edu.add(new regiNewStudent(edunew));
    }

    public void setOrder() throws Exception {
        studentID.setText(manageStudentsBO.generateStudentId());

    }

    public boolean isNICValid(String nic){
        return nic.matches("\\d{9}[Vv]");
    }

    public boolean isValidEmail(String mail){

        return mail.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");

    }

}
