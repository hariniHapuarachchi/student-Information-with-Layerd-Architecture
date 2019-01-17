package lk.ijse.dep.dto;

public class ParentDetDTO extends SuperDTO{

    private String stId;
    private String parentName;
    private String parentMobile1;
    private String parentMobile2;
    private String parentMail;
    private String designation;
    private String workingPlace;
    private String workingAddress;

    public ParentDetDTO() {
    }

    public ParentDetDTO(String stId, String parentName, String parentMobile1, String parentMobile2, String parentMail, String designation, String workingPlace, String workingAddress) {
        this.stId = stId;
        this.parentName = parentName;
        this.parentMobile1 = parentMobile1;
        this.parentMobile2 = parentMobile2;
        this.parentMail = parentMail;
        this.designation = designation;
        this.workingPlace = workingPlace;
        this.workingAddress = workingAddress;
    }

    public String getStId() {
        return stId;
    }

    public void setStId(String stId) {
        this.stId = stId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentMobile1() {
        return parentMobile1;
    }

    public void setParentMobile1(String parentMobile1) {
        this.parentMobile1 = parentMobile1;
    }

    public String getParentMobile2() {
        return parentMobile2;
    }

    public void setParentMobile2(String parentMobile2) {
        this.parentMobile2 = parentMobile2;
    }

    public String getParentMail() {
        return parentMail;
    }

    public void setParentMail(String parentMail) {
        this.parentMail = parentMail;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getWorkingPlace() {
        return workingPlace;
    }

    public void setWorkingPlace(String workingPlace) {
        this.workingPlace = workingPlace;
    }

    public String getWorkingAddress() {
        return workingAddress;
    }

    public void setWorkingAddress(String workingAddress) {
        this.workingAddress = workingAddress;
    }

    @Override
    public String toString() {
        return "ParentDetDTO{" +
                "stId='" + stId + '\'' +
                ", parentName='" + parentName + '\'' +
                ", parentMobile1='" + parentMobile1 + '\'' +
                ", parentMobile2='" + parentMobile2 + '\'' +
                ", parentMail='" + parentMail + '\'' +
                ", designation='" + designation + '\'' +
                ", workingPlace='" + workingPlace + '\'' +
                ", workingAddress='" + workingAddress + '\'' +
                '}';
    }
}
