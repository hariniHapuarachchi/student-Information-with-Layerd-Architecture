package lk.ijse.dep.dto;

import java.util.List;

public class StudentWithBatchDTO extends SuperDTO{

    private String batch;
    private String student;
   private List<StudentWithBatchDTO> tmpstWithBatchDTOS;



    public StudentWithBatchDTO() {
    }

    public StudentWithBatchDTO(String batch, String student) {
        this.batch = batch;
        this.student = student;
    }

    public StudentWithBatchDTO(String batch, String student, List<StudentWithBatchDTO> tmpstWithBatchDTOS) {
        this.batch = batch;
        this.student = student;
        this.setTmpstWithBatchDTOS(tmpstWithBatchDTOS);
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

    public List<StudentWithBatchDTO> getTmpstWithBatchDTOS() {
        return tmpstWithBatchDTOS;
    }

    public void setTmpstWithBatchDTOS(List<StudentWithBatchDTO> tmpstWithBatchDTOS) {
        this.tmpstWithBatchDTOS = tmpstWithBatchDTOS;
    }

    @Override
    public String toString() {
        return "StudentWithBatchDTO{" +
                "batch='" + batch + '\'' +
                ", student='" + student + '\'' +
                ", tmpstWithBatchDTOS=" + tmpstWithBatchDTOS +
                '}';
    }
}
