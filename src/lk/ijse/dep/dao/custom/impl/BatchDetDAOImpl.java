package lk.ijse.dep.dao.custom.impl;

import lk.ijse.dep.dao.CrudUtil;
import lk.ijse.dep.dao.custom.BatchDetDAO;
import lk.ijse.dep.entity.BatchDet;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BatchDetDAOImpl implements BatchDetDAO {
    @Override
    public Optional<BatchDet> find(String batchId) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM batchDet WHERE bId=?", batchId);
        BatchDet b = null;
        if (rst.next()) {
            b = new BatchDet(rst.getString("cours"),
                    rst.getString("bId"),
                    rst.getString("startDate"),
                    rst.getString("description"),
                    rst.getString("capacity"));
        }
        return Optional.ofNullable(b);
    }

    @Override
    public Optional<List<BatchDet>> findAll() throws Exception {
        ArrayList<BatchDet> batchDets = new ArrayList<>();
        ResultSet rst = CrudUtil.<ResultSet>execute("SELECT * FROM batchDet");
        while (rst.next()) {
            String course = rst.getString(1);
            String bId = rst.getString(2);
            String startDate = rst.getString(3);
            String description = rst.getString(4);
            String capacity = rst.getString(5);
            BatchDet batchDet = new BatchDet(course,bId,startDate,description,capacity);
            batchDets.add(batchDet);
        }
        return Optional.ofNullable(batchDets);
    }

    @Override
    public boolean save(BatchDet batchDet) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO batchDet VALUES(?,?,?,?,?)",
                batchDet.getCours(),batchDet.getbId(),batchDet.getStartDate(),batchDet.getDescription(),batchDet.getCapacity())>0;
    }

    @Override
    public boolean update(BatchDet batchDet) throws Exception {
        return CrudUtil.<Integer>execute("UPDATE batchDet SET course=?,startDate=?,description=?,capacity=? WHERE bId = ?",
                batchDet.getCours(),batchDet.getStartDate(),batchDet.getDescription(),batchDet.getCapacity(),batchDet.getbId())>0;
    }

    @Override
    public boolean delete(String batchId) throws Exception {
        return CrudUtil.<Integer>execute("DELETE from batchDet WHERE bId = ?",batchId)>0;
    }
}
