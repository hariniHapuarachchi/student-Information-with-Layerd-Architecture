package lk.ijse.dep.business.custom;

import lk.ijse.dep.business.SuperBO;
import lk.ijse.dep.dto.*;

import java.util.List;

public interface ManageStudentsBO extends SuperBO {

    List<StudentDetDTO> getStudents() throws Exception;

    String generateStudentId() throws Exception;

    void CreateStudents(StudentDetDTO dto) throws Exception;

    boolean updateStudents(StudentDetDTO dto) throws Exception;

    boolean deleteStudents(String studentID) throws Exception;

    StudentDetDTO findStudents(String id) throws Exception;


    List<ParentDetDTO> getParent() throws Exception;

    void CreateParent(ParentDetDTO dto) throws Exception;

    boolean updateParent(ParentDetDTO dto) throws Exception;

    boolean deleteParent(String studentID) throws Exception;

    ParentDetDTO findParent(String id) throws Exception;


    List<StudentQualificationDTO> getQualification() throws Exception;

    String generateQualificationId() throws Exception;

    void CreateQualification(StudentQualificationDTO dto) throws Exception;

    boolean updateQualification(StudentQualificationDTO dto) throws Exception;

    boolean deleteQualification(String qualID) throws Exception;

    StudentQualificationDTO findQualification(String id) throws Exception;


    List<StudentWithBatchDTO> getStudentsWithBatches() throws Exception;

    boolean createStudentWithBatch(StudentWithBatchDTO dto) throws Exception;

    boolean updateStudentsWithBatch(StudentWithBatchDTO dto) throws Exception;

    boolean deleteStudentsWithBatch(String studentID) throws Exception;

    StudentWithBatchDTO findStudentsWithBatch(String id) throws Exception;



}
