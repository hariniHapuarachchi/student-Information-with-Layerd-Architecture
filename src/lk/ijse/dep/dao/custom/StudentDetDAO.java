package lk.ijse.dep.dao.custom;

import lk.ijse.dep.dao.CrudDAO;
import lk.ijse.dep.entity.StudentDet;

public interface StudentDetDAO extends CrudDAO<StudentDet,String> {

    int count() throws Exception;
}
