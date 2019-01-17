package lk.ijse.dep.entity;

public class StudentWithBatch extends SuperEntity{

    private StudentWithBatchPK studentWithBatchPK;


    public StudentWithBatch() {
    }

    public StudentWithBatch(StudentWithBatchPK studentWithBatchPK) {
        this.studentWithBatchPK = studentWithBatchPK;

    }

    public StudentWithBatch(String student, String batch) {
        this.studentWithBatchPK = new StudentWithBatchPK(student, batch);
    }
    public StudentWithBatchPK getStudentWithBatchPK() {
        return studentWithBatchPK;
    }

    public void setStudentWithBatchPK(StudentWithBatchPK studentWithBatchPK) {
        this.studentWithBatchPK = studentWithBatchPK;
    }


}
