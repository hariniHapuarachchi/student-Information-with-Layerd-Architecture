package lk.ijse.dep.dao.custom.impl;

import lk.ijse.dep.dao.CrudUtil;
import lk.ijse.dep.dao.custom.StudentWithBatchDAO;
import lk.ijse.dep.entity.BatchDet;
import lk.ijse.dep.entity.StudentWithBatch;
import lk.ijse.dep.entity.StudentWithBatchPK;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentWithBatchDAOImpl implements StudentWithBatchDAO {
    @Override
    public Optional<StudentWithBatch> find(StudentWithBatchPK key) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM studentWithBatch WHERE batch=? AND student=?",key.getBatch(),key.getStudent());
        StudentWithBatch sb = null;
        if (rst.next()) {
            String student = rst.getString("batch");
            String batch = rst.getString("student");
            sb = new StudentWithBatch(student,batch);
        }
        return Optional.ofNullable(sb);
    }

    @Override
    public Optional<List<StudentWithBatch>> findAll() throws Exception {
        ArrayList<StudentWithBatch> studentWithBatches = new ArrayList<>();
        ResultSet rst = CrudUtil.<ResultSet>execute("SELECT * FROM studentWithBatch");
        while (rst.next()) {

            String batch = rst.getString(1);
            String student = rst.getString(2);

            StudentWithBatch studentWithBatch = new StudentWithBatch(batch,student);
            studentWithBatches.add(studentWithBatch);
        }
        return Optional.ofNullable(studentWithBatches);
    }

    @Override
    public boolean save(StudentWithBatch studentWithBatch) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO studentWithBatch VALUES(?,?)",
               studentWithBatch.getStudentWithBatchPK().getBatch(),studentWithBatch.getStudentWithBatchPK().getStudent())>0;
    }

    @Override
    public boolean update(StudentWithBatch studentWithBatch) throws Exception {
        return false;
    }

    @Override
    public boolean delete(StudentWithBatchPK key) throws Exception {
        return CrudUtil.<Integer>execute("DELETE from studentWithBatch WHERE batch = ? AND student=?",key.getBatch(),key.getStudent())>0;
    }
}
