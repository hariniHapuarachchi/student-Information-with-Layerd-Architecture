package lk.ijse.dep.dao.custom.impl;

import lk.ijse.dep.dao.CrudUtil;
import lk.ijse.dep.dao.custom.ParentDetDAO;
import lk.ijse.dep.entity.BatchDet;
import lk.ijse.dep.entity.ParentDet;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParentDetDAOImpl implements ParentDetDAO {
    @Override
    public Optional<ParentDet> find(String studentId) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM parentDet WHERE stId=?", studentId);
        ParentDet p = null;
        if (rst.next()) {
            p = new ParentDet(rst.getString("stId"),
                    rst.getString("parentName"),
                    rst.getString("parentMobile1"),
                    rst.getString("parentMobile2"),
                    rst.getString("parentMail"),
                    rst.getString("designation"),
                    rst.getString("workingPlace"),
                    rst.getString("workingAddress"));
        }
        return Optional.ofNullable(p);
    }

    @Override
    public Optional<List<ParentDet>> findAll() throws Exception {
        ArrayList<ParentDet> parentDets = new ArrayList<>();
        ResultSet rst = CrudUtil.<ResultSet>execute("SELECT * FROM parentDet");
        while (rst.next()) {
            String stId = rst.getString(1);
            String parentName = rst.getString(2);
            String parentMobile1 = rst.getString(3);
            String parentMobile2 = rst.getString(4);
            String parentMail = rst.getString(5);
            String designation = rst.getString(6);
            String workingPlace = rst.getString(7);
            String workingAddress = rst.getString(8);
            ParentDet parentDet = new ParentDet(stId,parentName,parentMobile1,parentMobile2,parentMail,designation,workingPlace,workingAddress);
            parentDets.add(parentDet);
        }
        return Optional.ofNullable(parentDets);
    }

    @Override
    public boolean save(ParentDet parentDet) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO parentDet VALUES(?,?,?,?,?,?,?,?)",
                parentDet.getStId(),parentDet.getParentName(),parentDet.getParentMobile1(),parentDet.getParentMobile2(),
                parentDet.getParentMail(),parentDet.getDesignation(),parentDet.getWorkingPlace(),parentDet.getWorkingAddress())>0;
    }

    @Override
    public boolean update(ParentDet parentDet) throws Exception {
        return CrudUtil.<Integer>execute("UPDATE parentDet SET parentName=?,parentMobile1=?,parentMobile2=?,parentMail=?,designation=?,workingPlace=?,workingAddress=? WHERE stId = ?",
                parentDet.getParentName(),parentDet.getParentMobile1(),parentDet.getParentMobile2(),
                parentDet.getParentMail(),parentDet.getDesignation(),parentDet.getWorkingPlace(),parentDet.getWorkingAddress(),parentDet.getStId())>0;
    }

    @Override
    public boolean delete(String studentId) throws Exception {
        return CrudUtil.<Integer>execute("DELETE from parentDet WHERE stId = ?",studentId)>0;
    }
}
