package lk.ijse.dep.dao.custom;

import lk.ijse.dep.dao.CrudDAO;
import lk.ijse.dep.entity.StudentQualification;

public interface StudentQualificationDAO extends CrudDAO<StudentQualification,String> {

    int count() throws Exception;
}
