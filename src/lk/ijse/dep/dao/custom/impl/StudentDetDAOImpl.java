package lk.ijse.dep.dao.custom.impl;

import lk.ijse.dep.dao.CrudUtil;
import lk.ijse.dep.dao.custom.StudentDetDAO;
import lk.ijse.dep.entity.BatchDet;
import lk.ijse.dep.entity.StudentDet;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDetDAOImpl implements StudentDetDAO {
    @Override
    public Optional<StudentDet> find(String studentId) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM studentDet WHERE sId=?", studentId);
        StudentDet s = null;
        if (rst.next()) {
            s = new StudentDet(rst.getString("sId"),
                    rst.getString("sName"),
                    rst.getString("fullName"),
                    rst.getString("address"),
                    rst.getString("city"),
                    rst.getString("teleHome"),
                    rst.getString("email"),
                    rst.getString("dob"),
                    rst.getString("gender"),
                    rst.getString("nic"),
                    rst.getString("school"),
                    rst.getString("uni"),
                    rst.getString("faculty"),
                    rst.getString("teleMobile"),
                    rst.getString("higherEd"));
        }
        return Optional.ofNullable(s);
    }

    @Override
    public Optional<List<StudentDet>> findAll() throws Exception {
        ArrayList<StudentDet> studentDets = new ArrayList<>();
        ResultSet rst = CrudUtil.<ResultSet>execute("SELECT * FROM studentDet");
        while (rst.next()) {
            String sId = rst.getString(1);
            String sName = rst.getString(2);
            String fullName = rst.getString(3);
            String address = rst.getString(4);
            String city = rst.getString(5);
            String teleHome = rst.getString(6);
            String email = rst.getString(7);
            String dob = rst.getString(8);
            String gender = rst.getString(9);
            String nic = rst.getString(10);
            String school = rst.getString(11);
            String uni = rst.getString(12);
            String faculty = rst.getString(13);
            String teleMobile = rst.getString(14);
            String higherEd = rst.getString(15);

            StudentDet studentDet = new StudentDet(sId,sName,fullName,address,city,teleHome,email,dob,gender,nic,
                    school,uni,faculty,teleMobile,higherEd);
            studentDets.add(studentDet);
        }
        return Optional.ofNullable(studentDets);
    }

    @Override
    public boolean save(StudentDet studentDet) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO studentDet VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                studentDet.getsId(),studentDet.getsName(),studentDet.getFullName(),studentDet.getAddress(),studentDet.getCity(),studentDet.getTeleHome(),
                studentDet.getEmail(),studentDet.getDob(),studentDet.getGender(),studentDet.getNic(),studentDet.getSchool(),
                studentDet.getUni(),studentDet.getFaculty(),studentDet.getTeleMobile(),studentDet.getHigherEd())>0;
    }

    @Override
    public boolean update(StudentDet studentDet) throws Exception {
        return CrudUtil.<Integer>execute("UPDATE studentDet SET sName=?,fullName=?,address=?,city=?,teleHome=?,email=?,dob=?,gender=?,nic=?,school=?,uni=?,faculty=?,teleMobile=?,higherEd=? WHERE sId = ?",
                studentDet.getsName(),studentDet.getFullName(),studentDet.getAddress(),studentDet.getCity(),studentDet.getTeleHome(),
                studentDet.getEmail(),studentDet.getDob(),studentDet.getGender(),studentDet.getNic(),studentDet.getSchool(),
                studentDet.getUni(),studentDet.getFaculty(),studentDet.getTeleMobile(),studentDet.getHigherEd(), studentDet.getsId())>0;
    }

    @Override
    public boolean delete(String studentId) throws Exception {
        return CrudUtil.<Integer>execute("DELETE from studentDet WHERE sId = ?",studentId)>0;
    }

    @Override
    public int count() throws Exception {

            ResultSet rst = CrudUtil.execute("SELECT COUNT(*) FROM studentDet");
            if (rst.next()) {
                return rst.getInt(1);
            }
            return 0;

    }
}
