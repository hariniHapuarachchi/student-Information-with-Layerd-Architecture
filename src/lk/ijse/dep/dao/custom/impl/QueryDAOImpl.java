package lk.ijse.dep.dao.custom.impl;

import lk.ijse.dep.dao.CrudUtil;
import lk.ijse.dep.dao.custom.QueryDAO;
import lk.ijse.dep.entity.CustomEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public Optional<List<CustomEntity>> findAllCourseWithStudentIdAndBatchId(String studentId,String batchId) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT studentWithBatch.student,b.course,studentWithBatch.batch FROM studentWithBatch\n" +
                " INNER JOIN batchDet b on studentWithBatch.batch = b.bId WHERE studentWithBatch.batch=? AND studentWithBatch.student=?", batchId,studentId);

        List<CustomEntity> al = new ArrayList<>();

        while (rst.next()) {
            CustomEntity customEntity = new CustomEntity(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3));

            al.add(customEntity);
        }
        return Optional.ofNullable(al);
    }
}
