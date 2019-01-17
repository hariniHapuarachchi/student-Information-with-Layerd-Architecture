package lk.ijse.dep.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.business.BOFactory;
import lk.ijse.dep.business.custom.ManageBatchesBO;
import lk.ijse.dep.business.custom.ManageStudentsBO;
import lk.ijse.dep.db.DBConnection;
import lk.ijse.dep.dto.BatchDetDTO;
import lk.ijse.dep.dto.CourseDetDTO;
import lk.ijse.dep.dto.ParentDetDTO;
import lk.ijse.dep.view.util.BatchDetTM;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageParentController {

    @FXML
    private AnchorPane fxparent;
    @FXML
    private TableView parentTable;
    @FXML
    private TextField pID;
    @FXML
    private TextField pname;
    @FXML
    private TextField pm1;
    @FXML
    private TextField pm2;
    @FXML
    private TextField pmail;
    @FXML
    private TextField pdes;
    @FXML
    private TextField pPlace;
    @FXML
    private TextField paddress;
    Connection conn = null;

    private ManageStudentsBO manageStudentsBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_STUDENTS);

    public void initialize() {

        pID.setText(ManageNewStudentController.ID);
        pID.setEditable(false);
    }

    public void parentAdd(ActionEvent actionEvent) throws IOException, SQLException {


        if (pID.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Parent ID is empty", ButtonType.OK).showAndWait();
            pID.requestFocus();
            return;
        } else if (pname.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Parent Name is empty", ButtonType.OK).showAndWait();
            pname.requestFocus();
            return;
        } else if (pm1.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Mobile1 is empty", ButtonType.OK).showAndWait();
            pm1.requestFocus();
            return;
        }else if (pm2.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Mobile2 is empty", ButtonType.OK).showAndWait();
            pm2.requestFocus();
            return;
        }else if (pmail.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Email is empty", ButtonType.OK).showAndWait();
            pmail.requestFocus();
            return;
        }else if (pdes.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Designation is empty", ButtonType.OK).showAndWait();
            pdes.requestFocus();
            return;
        }else if (pPlace.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Place is empty", ButtonType.OK).showAndWait();
            pPlace.requestFocus();
            return;
        }else if (paddress.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Address is empty", ButtonType.OK).showAndWait();
            paddress.requestFocus();
            return;
        }

        if (!isValidPhone(pm1.getText()) || !isValidPhone(pm2.getText())){
            new Alert(Alert.AlertType.ERROR, "Invalid Mobile Number", ButtonType.OK).show();
            return;
        }else if (!isValidPhone(pm1.getText()) && !isValidPhone(pm2.getText())){
            new Alert(Alert.AlertType.ERROR, "Both numbers are invalid", ButtonType.OK).show();
            return;
        }else if (pm1.getText().length() != 11 || pm2.getText().length() != 11){
            new Alert(Alert.AlertType.ERROR, "Mobile number should have 11 elements with '-'", ButtonType.OK).show();
            return;
        }else if (pm1.getText().length() != 11 && pm2.getText().length() != 11){
            new Alert(Alert.AlertType.ERROR, "Both numbers should have 11 elements with '-'", ButtonType.OK).show();
            return;
        }


            ParentDetDTO parentDetDTO = null;
            try {
                parentDetDTO = manageStudentsBO.findParent(pID.getText());
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }

//            if (parentDetDTO == null) {
//                new Alert(Alert.AlertType.ERROR, "Invalid Parent ID", ButtonType.OK).showAndWait();
//                pID.requestFocus();
//                return;
//            }

            ParentDetDTO parentDetDTO1 = new ParentDetDTO(pID.getText(), pname.getText(), pm1.getText(),pm2.getText(),pmail.getText(),pdes.getText(),pPlace.getText(),paddress.getText());
            boolean result4 = false;
            try {
//                conn = DBConnection.getConnection();
//                conn.setAutoCommit(false);
                manageStudentsBO.CreateParent(parentDetDTO1);
//                DBConnection.getConnection().commit();
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
//                if (DBConnection.getConnection() != null){
//                    try{
//                        DBConnection.getConnection().rollback();
//                    }catch (SQLException ex){
//                        ex.printStackTrace();
//                    }
//                }
//            }finally {
//                DBConnection.getConnection().setAutoCommit(true);
//            }

            resest();
    }

    public void parentHome(ActionEvent actionEvent) throws IOException {

        Parent pt = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/view/mainPage.fxml"));
        Scene scene = new Scene(pt);
        Stage primaryStage = (Stage) fxparent.getScene().getWindow();
        primaryStage.setScene(scene);
    }

    public boolean isValidPhone(String tele){
        return tele.matches("\\d{3}-\\d{7}");
    }

    public void resest(){

        pID.setEditable(false);
        pname.setEditable(false);
        pm1.setEditable(false);
        pm2.setEditable(false);
        pmail.setEditable(false);
        pdes.setEditable(false);
        pPlace.setEditable(false);
        paddress.setEditable(false);


        pID.clear();
        pname.clear();
        pm1.clear();
        pm2.clear();
        pmail.clear();
        pdes.clear();
        pPlace.clear();
        paddress.clear();

    }
}
