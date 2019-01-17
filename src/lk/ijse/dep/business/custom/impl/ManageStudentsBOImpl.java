package lk.ijse.dep.business.custom.impl;

import lk.ijse.dep.business.Converter;
import lk.ijse.dep.business.custom.ManageStudentsBO;
import lk.ijse.dep.dao.DAOFactory;
import lk.ijse.dep.dao.custom.*;
import lk.ijse.dep.db.DBConnection;
import lk.ijse.dep.dto.*;
import lk.ijse.dep.entity.StudentWithBatch;

import java.util.ArrayList;
import java.util.List;

public class ManageStudentsBOImpl implements ManageStudentsBO {

    private StudentDetDAO studentDetDAO;
    private StudentQualificationDAO studentQualificationDAO;
    private ParentDetDAO parentDetDAO;
    private StudentWithBatchDAO studentWithBatchDAO;
    private QueryDAO queryDAO;

    public ManageStudentsBOImpl() {

        studentDetDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);
        parentDetDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PARENT);
        studentQualificationDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUALIFICATION);
        studentWithBatchDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT_BATCH);
        queryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    }

    @Override
    public List<StudentDetDTO> getStudents() throws Exception {
        return studentDetDAO.findAll().map(Converter::<StudentDetDTO>getDTOList).get();
    }

    @Override
    public String generateStudentId() throws Exception {

        return studentDetDAO.count() + 1 + "";
    }

    @Override
    public void CreateStudents(StudentDetDTO dto) throws Exception {


        DBConnection.getConnection().setAutoCommit(false);

        try {

            boolean result =  studentDetDAO.save(Converter.getEntity(dto));

            if (!result) {
                DBConnection.getConnection().rollback();
                return;
            }

            //DBConnection.getConnection().commit();

        } catch (Exception ex) {
            DBConnection.getConnection().rollback();
            ex.printStackTrace();
//        } finally {
//            DBConnection.getConnection().setAutoCommit(true);
//        }
    }
    }

    @Override
    public boolean updateStudents(StudentDetDTO dto) throws Exception {
        return studentDetDAO.update(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteStudents(String studentID) throws Exception {
        return studentDetDAO.delete(studentID);
    }

    @Override
    public StudentDetDTO findStudents(String id) throws Exception {
        return studentDetDAO.find(id).map(Converter::<StudentDetDTO>getDTO).orElse(null);
    }

    @Override
    public List<ParentDetDTO> getParent() throws Exception {
        return parentDetDAO.findAll().map(Converter::<ParentDetDTO>getDTOList).get();
    }

    @Override
    public void CreateParent(ParentDetDTO dto) throws Exception {

        //DBConnection.getConnection().setAutoCommit(false);

        try {

            boolean result1= parentDetDAO.save(Converter.getEntity(dto));

            if (!result1) {
                DBConnection.getConnection().rollback();
                return;
            }

            DBConnection.getConnection().commit();

        } catch (Exception ex) {
            DBConnection.getConnection().rollback();
            ex.printStackTrace();
        } finally {
            DBConnection.getConnection().setAutoCommit(true);
        }

    }

    @Override
    public boolean updateParent(ParentDetDTO dto) throws Exception {
        return parentDetDAO.update(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteParent(String studentID) throws Exception {
        return parentDetDAO.delete(studentID);
    }

    @Override
    public ParentDetDTO findParent(String id) throws Exception {
        return parentDetDAO.find(id).map(Converter::<ParentDetDTO>getDTO).orElse(null);
    }

    @Override
    public List<StudentQualificationDTO> getQualification() throws Exception {
        return studentQualificationDAO.findAll().map(Converter::<StudentQualificationDTO>getDTOList).get();
    }

    @Override
    public String generateQualificationId() throws Exception {
        return studentQualificationDAO.count() + 1 + "";
    }

    @Override
    public void CreateQualification(StudentQualificationDTO dto) throws Exception {

        try {

            boolean result2= studentQualificationDAO.save(Converter.getEntity(dto));

            if (!result2) {
                DBConnection.getConnection().rollback();
                return;
            }


        } catch (Exception ex) {

            DBConnection.getConnection().rollback();
            ex.printStackTrace();
        }

    }

    @Override
    public boolean updateQualification(StudentQualificationDTO dto) throws Exception {
        return studentQualificationDAO.update(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteQualification(String qualID) throws Exception {
        return studentQualificationDAO.delete(qualID);
    }

    @Override
    public StudentQualificationDTO findQualification(String id) throws Exception {
        return studentQualificationDAO.find(id).map(Converter::<StudentQualificationDTO>getDTO).orElse(null);
    }

    @Override
    public List<StudentWithBatchDTO> getStudentsWithBatches() throws Exception {
        //return studentQualificationDAO.findAll().map(Converter::<StudentWithBatchDTO>getDTOList).get();
        List<StudentWithBatch> studentWithBatches = studentWithBatchDAO.findAll().get();
        ArrayList<StudentWithBatchDTO> tmpDTOs = new ArrayList<>();

        for (StudentWithBatch studentWithBatch : studentWithBatches) {
            List<StudentWithBatchDTO> tmpstWithBatchDTOS = queryDAO.findAllCourseWithStudentIdAndBatchId(studentWithBatch.getStudentWithBatchPK().getBatch(), studentWithBatch.getStudentWithBatchPK().getBatch()).map(ce -> {
                return Converter.getDTOList(ce, StudentWithBatchDTO.class);
            }).get();

            StudentWithBatchDTO dto = new StudentWithBatchDTO(studentWithBatch.getStudentWithBatchPK().getBatch(),
                    studentWithBatch.getStudentWithBatchPK().getStudent(),tmpstWithBatchDTOS);
            tmpDTOs.add(dto);
        }

        return tmpDTOs;
    }

    @Override
    public boolean createStudentWithBatch(StudentWithBatchDTO dto) throws Exception {
        return studentWithBatchDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updateStudentsWithBatch(StudentWithBatchDTO dto) throws Exception {
        return studentWithBatchDAO.update(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteStudentsWithBatch(String studentID) throws Exception {
        return false;
    }


    @Override
    public StudentWithBatchDTO findStudentsWithBatch(String id) throws Exception {

        return null;
    }
}
