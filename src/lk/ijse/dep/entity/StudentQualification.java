package lk.ijse.dep.entity;

public class StudentQualification extends SuperEntity{

    private String qualID;
    private String studentId;
    private String qualification;
    private String institute;
    private String awardDate;
    private String specialization;

    public StudentQualification() {
    }

    public StudentQualification(String qualID, String studentId, String qualification, String institute, String awardDate, String specialization) {
        this.qualID = qualID;
        this.studentId = studentId;
        this.qualification = qualification;
        this.institute = institute;
        this.awardDate = awardDate;
        this.specialization = specialization;
    }

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

    @Override
    public String toString() {
        return "StudentQualification{" +
                "qualID='" + qualID + '\'' +
                ", studentId='" + studentId + '\'' +
                ", qualification='" + qualification + '\'' +
                ", institute='" + institute + '\'' +
                ", awardDate='" + awardDate + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
