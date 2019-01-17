package lk.ijse.dep.dao;

import lk.ijse.dep.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    public enum DAOTypes{
        BATCH,COURSE,PARENT,STUDENT, QUALIFICATION,STUDENT_BATCH,QUERY;
    }


    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoType) {
        switch (daoType) {
            case BATCH:
                return (T) new BatchDetDAOImpl();
            case COURSE:
                return (T) new CourseDetDAOImpl();
            case PARENT:
                return (T) new ParentDetDAOImpl();
            case STUDENT:
                return (T) new StudentDetDAOImpl();
            case QUALIFICATION:
                return (T) new StudentQualificationDAOImpl();
            case STUDENT_BATCH:
                return (T) new StudentWithBatchDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            default:
                return null;
        }
    }
}
