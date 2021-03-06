package lk.ijse.dep.dto;

public class CourseDetDTO extends SuperDTO{

    private String cid;
    private String cname;
    private String description;
    private String duration;

    public CourseDetDTO() {
    }

    public CourseDetDTO(String cid, String cname, String description, String duration) {
        this.cid = cid;
        this.cname = cname;
        this.description = description;
        this.duration = duration;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "CourseDetDTO{" +
                "cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                ", description='" + description + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
