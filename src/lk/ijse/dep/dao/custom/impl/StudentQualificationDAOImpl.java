package lk.ijse.dep.dao.custom.impl;

import lk.ijse.dep.dao.CrudUtil;
import lk.ijse.dep.dao.custom.StudentQualificationDAO;
import lk.ijse.dep.entity.BatchDet;
import lk.ijse.dep.entity.StudentQualification;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentQualificationDAOImpl implements StudentQualificationDAO {
    @Override
    public Optional<StudentQualification> find(String studentId) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM studentQualification WHERE studentId=?", studentId);
        StudentQualification sq = null;
        if (rst.next()) {
            sq = new StudentQualification(rst.getString("qualID"),
                    rst.getString("studentId"),
                    rst.getString("qualification"),
                    rst.getString("institute"),
                    rst.getString("awardDate"),
                    rst.getString("specialization"));
        }
        return Optional.ofNullable(sq);
    }

    @Override
    public Optional<List<StudentQualification>> findAll() throws Exception {
        ArrayList<StudentQualification> studentQualifications = new ArrayList<>();
        ResultSet rst = CrudUtil.<ResultSet>execute("SELECT * FROM studentQualification");
        while (rst.next()) {
            String qualID = rst.getString(1);
            String studentId = rst.getString(2);
            String qualification = rst.getString(3);
            String institute = rst.getString(4);
            String awardDate = rst.getString(5);
            String specialization = rst.getString(6);
            StudentQualification studentQualification = new StudentQualification(qualID,studentId,qualification,institute,awardDate,specialization);
            studentQualifications.add(studentQualification);
        }
        return Optional.ofNullable(studentQualifications);
    }

    @Override
    public boolean save(StudentQualification studentQualification) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO studentQualification VALUES(?,?,?,?,?,?)",
                studentQualification.getQualID(),studentQualification.getStudentId(),studentQualification.getQualification(),studentQualification.getInstitute(),studentQualification.getAwardDate(),studentQualification.getSpecialization())>0;
    }

    @Override
    public boolean update(StudentQualification studentQualification) throws Exception {
        return CrudUtil.<Integer>execute("UPDATE studentQualification SET qualification=?,institute=?,awardDate=?,specialization=? WHERE qualID = ?",
                studentQualification.getQualification(),studentQualification.getInstitute(),studentQualification.getAwardDate(),studentQualification.getSpecialization(),studentQualification.getQualID())>0;
    }

    @Override
    public boolean delete(String qualID) throws Exception {
        return CrudUtil.<Integer>execute("DELETE from studentQualification WHERE qualID = ?",qualID)>0;
    }

    @Override
    public int count() throws Exception {

        ResultSet rst = CrudUtil.execute("SELECT COUNT(*) FROM studentQualification");
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;

    }
}
