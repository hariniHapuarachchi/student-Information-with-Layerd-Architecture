package lk.ijse.dep.view.util;

import lk.ijse.dep.dto.StudentWithBatchDTO;

public class StudentWithBatchTM {

    private String batch;
    private String student;

    public StudentWithBatchTM() {
    }


    public StudentWithBatchTM(String batch, String student) {
        this.batch=batch;
        this.student=student;
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

    @Override
    public String toString() {
        return "StudentWithBatchTM{" +
                "batch='" + batch + '\'' +
                ", student='" + student + '\'' +
                '}';
    }
}

