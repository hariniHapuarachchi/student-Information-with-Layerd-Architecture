package lk.ijse.dep.entity;

public class StudentWithBatchPK {

    private String batch;
    private String student;

    public StudentWithBatchPK() {
    }

    public StudentWithBatchPK(String batch, String student) {
        this.batch = batch;
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentWithBatchPK{" +
                "batch='" + batch + '\'' +
                ", student='" + student + '\'' +
                '}';
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }
}
