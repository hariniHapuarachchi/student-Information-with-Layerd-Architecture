package lk.ijse.dep.entity;

public class BatchDet extends SuperEntity{

    private String cours;
    private String bId;
    private String startDate;
    private String description;
    private String capacity;


    public BatchDet() {
    }

    public BatchDet(String cours, String bId, String startDate, String description, String capacity) {
        this.cours = cours;
        this.bId = bId;
        this.startDate = startDate;
        this.description = description;
        this.capacity = capacity;
    }

    public String getCours() {
        return cours;
    }

    public void setCours(String cours) {
        this.cours = cours;
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
        return "BatchDet{" +
                "cours='" + cours + '\'' +
                ", bId='" + bId + '\'' +
                ", startDate='" + startDate + '\'' +
                ", description='" + description + '\'' +
                ", capacity='" + capacity + '\'' +
                '}';
    }
}
