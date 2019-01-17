package lk.ijse.dep.business;

import lk.ijse.dep.business.custom.impl.*;

public class BOFactory {

    public enum BOTypes{
        MANAGE_BATCHES,MANAGE_COURSES,MANAGE_STUDENTS
    }

    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getInstance(){
        if (boFactory == null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boType){
        switch (boType){
            case MANAGE_BATCHES:
                return (T) new ManageBatchesBOImpl();
            case MANAGE_COURSES:
                return (T) new ManageCoursesBOImpl();
            case MANAGE_STUDENTS:
                return (T) new ManageStudentsBOImpl();
            default:
                return null;
        }
    }
}
