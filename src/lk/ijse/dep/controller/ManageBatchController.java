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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.business.BOFactory;
import lk.ijse.dep.business.custom.ManageBatchesBO;
import lk.ijse.dep.business.custom.ManageCoursesBO;
import lk.ijse.dep.dto.BatchDetDTO;
import lk.ijse.dep.dto.CourseDetDTO;
import lk.ijse.dep.view.util.BatchDetTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageBatchController {

    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private AnchorPane fxBatch;
    @FXML
    private TextField batchCourse;
    @FXML
    private TextField batchId;
    @FXML
    private TextField batchDate;
    @FXML
    private TextField batchDes;
    @FXML
    private TextField batchCap;
    @FXML
    private TableView<BatchDetTM> batchTable;

    private ManageBatchesBO manageBatchesBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_BATCHES);
    private ManageCoursesBO manageCoursesBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_COURSES);

    public void initialize(){

        batchTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("cours"));
        batchTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("bId"));
        batchTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("startDate"));
        batchTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("description"));
        batchTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("capacity"));

        btnSave.setDisable(true);
        btnDelete.setDisable(true);

        List<BatchDetDTO> batchesDB = null;
        try {
            batchesDB = manageBatchesBO.getBatches();
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        ObservableList<BatchDetDTO> batchDetDTOS = FXCollections.observableArrayList(batchesDB);
        ObservableList<BatchDetTM> tblItems = FXCollections.observableArrayList();

        for (BatchDetDTO batchDetDTO : batchDetDTOS) {
            tblItems.add(new BatchDetTM(batchDetDTO.getCours(), batchDetDTO.getbId(),batchDetDTO.getStartDate(),batchDetDTO.getDescription(),batchDetDTO.getCapacity()));
        }


        batchTable.setItems(tblItems);
        
        batchTable.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<BatchDetTM>() {
                    @Override

                    public void changed(ObservableValue<? extends BatchDetTM> ov, BatchDetTM old_val, BatchDetTM new_val) {

                        if (new_val == null) {
                            return;
                        }

                        batchId.setEditable(false);

                        batchId.setText(new_val.getbId());
                        batchCourse.setText(new_val.getCours());
                        batchDate.setText(new_val.getStartDate());
                        batchDes.setText(new_val.getDescription());
                        batchCap.setText(new_val.getCapacity());

                        btnSave.setDisable(false);
                        btnDelete.setDisable(false);
                    }
                });
    }

    public void createNewBatch(ActionEvent actionEvent) {

        resest();
    }

    public void batchHome(ActionEvent actionEvent) throws IOException {

        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/view/mainPage.fxml"));
        Scene scene = new Scene(parent);
        Stage primaryStage = (Stage) fxBatch.getScene().getWindow();
        primaryStage.setScene(scene);
    }

    public void batchSave(ActionEvent actionEvent) {

        if (batchCourse.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Course ID is empty", ButtonType.OK).showAndWait();
            batchCourse.requestFocus();
            return;
        } else if (batchId.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Batch ID is empty", ButtonType.OK).showAndWait();
            batchId.requestFocus();
            return;
        } else if (batchDate.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Date is empty", ButtonType.OK).showAndWait();
            batchDate.requestFocus();
            return;
        }else if (batchDes.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Description is empty", ButtonType.OK).showAndWait();
            batchDes.requestFocus();
            return;
        }else if (batchCap.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Capacity is empty", ButtonType.OK).showAndWait();
            batchCap.requestFocus();
            return;
        }else if (!isValidStartDate(batchDate.getText())){
            new Alert(Alert.AlertType.ERROR, "Invalid Date Format", ButtonType.OK).show();
            batchDate.requestFocus();
            return;
        }


        if (batchTable.getSelectionModel().isEmpty()) {

            String courseID = batchCourse.getText();

            CourseDetDTO courseDetDTO = null;
            try {
                courseDetDTO = manageCoursesBO.findCourse(courseID);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }

            if (courseDetDTO == null) {
                new Alert(Alert.AlertType.ERROR, "Invalid Course ID", ButtonType.OK).showAndWait();
                batchCourse.requestFocus();
                return;
            }


            ObservableList<BatchDetTM> items = batchTable.getItems();
            for (BatchDetTM batchDetTM : items) {
                if (batchDetTM.getbId().equals(batchId.getText())) {
                    new Alert(Alert.AlertType.ERROR, "Duplicate Batch IDs are not allowed").showAndWait();
                    batchId.requestFocus();
                    return;
                }
            }


            BatchDetTM batchDetTM = new BatchDetTM(batchCourse.getText(),batchId.getText(), batchDate.getText(), batchDes.getText(),batchCap.getText());
            batchTable.getItems().add(batchDetTM);
            BatchDetDTO batchDetDTO = new BatchDetDTO(batchCourse.getText(), batchId.getText(), batchDate.getText(),batchDes.getText(),batchCap.getText());
            boolean result = false;
            try {
                result = manageBatchesBO.CreateBatch(batchDetDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }

            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Batch has been saved successfully", ButtonType.OK).showAndWait();
                batchTable.scrollTo(batchDetTM);
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save the Batch", ButtonType.OK).showAndWait();
            }

        } else {

            BatchDetTM selectedBatch = batchTable.getSelectionModel().getSelectedItem();
            selectedBatch.setCours(batchCourse.getText());
            selectedBatch.setStartDate(batchDate.getText());
            selectedBatch.setDescription(batchDes.getText());
            selectedBatch.setCapacity(batchCap.getText());
            batchTable.refresh();

            String selectedBatchID = batchTable.getSelectionModel().getSelectedItem().getbId();

            boolean result = false;
            try {
                result = manageBatchesBO.updateBatch(new BatchDetDTO(batchCourse.getText(),batchId.getText(),batchDate.getText(),batchDes.getText(),batchCap.getText()));
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }

            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Batch has been updated successfully", ButtonType.OK).showAndWait();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update the Batch", ButtonType.OK).showAndWait();
            }
        }

        resest();

    }

    public void batchDelete(ActionEvent actionEvent) {

        Alert confirmMsg = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this Batch?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = confirmMsg.showAndWait();

        if (buttonType.get() == ButtonType.YES) {
            String selectedBatch = batchTable.getSelectionModel().getSelectedItem().getbId();

            batchTable.getItems().remove(batchTable.getSelectionModel().getSelectedItem());
            boolean result = false;
            try {
                result = manageBatchesBO.deleteBatch(selectedBatch);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE, null, e);
            }
            if (!result){
                new Alert(Alert.AlertType.ERROR, "Failed to delete the Batch", ButtonType.OK).showAndWait();
            }else{
                resest();
            }
        }
    }

    public void resest(){

        batchId.setEditable(true);
        batchCourse.setEditable(true);
        batchDes.setEditable(true);
        batchDate.setEditable(true);
        batchCap.setEditable(true);


        batchId.clear();
        batchCourse.clear();
        batchDes.clear();
        batchDate.clear();
        batchCap.clear();

        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        batchTable.getSelectionModel().clearSelection();
    }

    public boolean isValidStartDate(String stDate){

        return stDate.matches("^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$");

    }
}
