package lk.ijse.dep.entity;

public class CustomEntity {

    private String batch;
    private String student;

    private String course;
    private String bId;
    private String startDate;
    private String description;
    private String capacity;

    public CustomEntity() {
    }

    public CustomEntity(String batch, String student, String course, String bId, String startDate, String description, String capacity) {
        this.batch = batch;
        this.student = student;
        this.course = course;
        this.bId = bId;
        this.startDate = startDate;
        this.description = description;
        this.capacity = capacity;
    }

    public CustomEntity(String batch, String student, String course) {
        this.batch = batch;
        this.student = student;
        this.course = course;
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getbId() {
        return bId;
    }

    public void setbId(String bId) {
        this.bId = bId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "CustomEntity{" +
                "batch='" + batch + '\'' +
                ", student='" + student + '\'' +
                ", course='" + course + '\'' +
                ", bId='" + bId + '\'' +
                ", startDate='" + startDate + '\'' +
                ", description='" + description + '\'' +
                ", capacity='" + capacity + '\'' +
                '}';
    }
}
