package lk.ijse.dep.dao.custom.impl;

import lk.ijse.dep.dao.CrudUtil;
import lk.ijse.dep.dao.custom.CourseDetDAO;
import lk.ijse.dep.entity.BatchDet;
import lk.ijse.dep.entity.CourseDet;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseDetDAOImpl implements CourseDetDAO {
    @Override
    public Optional<CourseDet> find(String courseId) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM courseDet WHERE cid=?", courseId);
        CourseDet c = null;
        if (rst.next()) {
            c = new CourseDet(rst.getString("cid"),
                    rst.getString("cname"),
                    rst.getString("description"),
                    rst.getString("duration"));
        }
        return Optional.ofNullable(c);
    }

    @Override
    public Optional<List<CourseDet>> findAll() throws Exception {
        ArrayList<CourseDet> courseDets = new ArrayList<>();
        ResultSet rst = CrudUtil.<ResultSet>execute("SELECT * FROM courseDet");
        while (rst.next()) {
            String cid = rst.getString("cid");
            String cname = rst.getString("cname");
            String description = rst.getString("description");
            String duration = rst.getString("duration");

            CourseDet courseDet = new CourseDet(cid,cname,description,duration);
            courseDets.add(courseDet);
        }
        return Optional.ofNullable(courseDets);
    }

    @Override
    public boolean save(CourseDet courseDet) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO courseDet VALUES(?,?,?,?)",
                courseDet.getCid(),courseDet.getCname(),courseDet.getDescription(),courseDet.getDuration())>0;
    }

    @Override
    public boolean update(CourseDet courseDet) throws Exception {
        return CrudUtil.<Integer>execute("UPDATE courseDet SET cname=?,description=?,duration=? WHERE cid = ?",
                courseDet.getCname(),courseDet.getDescription(),courseDet.getDuration(),courseDet.getCid())>0;
    }

    @Override
    public boolean delete(String courseId) throws Exception {
        return CrudUtil.<Integer>execute("DELETE from courseDet WHERE cId = ?",courseId)>0;
    }
}
