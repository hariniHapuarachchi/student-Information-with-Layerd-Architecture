package lk.ijse.dep.dto;

import java.util.ArrayList;

public class StudentQualificationDTO extends SuperDTO{

    private String qualID;
    private String studentId;
    private String qualification;
    private String institute;
    private String awardDate;
    private String specialization;
    private ArrayList<StudentQualificationDTO> studentQualificationDTOS;


    public StudentQualificationDTO() {
    }

//    public StudentQualificationDTO(String qualID, String studentId, String qualification, String institute, String awardDate, String specialization) {
//        this.qualID = qualID;
//        this.studentId = studentId;
//        this.qualification = qualification;
//        this.institute = institute;
//        this.awardDate = awardDate;
//        this.specialization = specialization;
//    }

    public StudentQualificationDTO(String qualID, String qualification, String institute, String awardDate, String specialization) {
        this.qualID = qualID;
        this.qualification = qualification;
        this.institute = institute;
        this.awardDate = awardDate;
        this.specialization = specialization;
    }

    public StudentQualificationDTO(String studentId, ArrayList<StudentQualificationDTO> studentQualificationDTOS) {
        this.studentId = studentId;
        this.setStudentQualificationDTOS(studentQualificationDTOS);

    }

//    public StudentQualificationDTO(String qualID, String studentId, String qualification, String institute, String awardDate, String specialization) {
//        this.studentId=studentId;
//        this.qualID = qualID;
//        this.qualification = qualification;
//        this.institute = institute;
//        this.awardDate = awardDate;
//        this.specialization = specialization;
//    }


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getAwardDate() {
        return awardDate;
    }

    public void setAwardDate(String awardDate) {
        this.awardDate = awardDate;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


    public String getQualID() {
        return qualID;
    }

    public void setQualID(String qualID) {
        this.qualID = qualID;
    }

    public ArrayList<StudentQualificationDTO> getStudentQualificationDTOS() {
        return studentQualificationDTOS;
    }

    public void setStudentQualificationDTOS(ArrayList<StudentQualificationDTO> studentQualificationDTOS) {
        this.studentQualificationDTOS = studentQualificationDTOS;
    }
}
