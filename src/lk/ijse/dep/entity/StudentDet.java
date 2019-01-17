package lk.ijse.dep.entity;

public class StudentDet extends SuperEntity{

    private String sId;
    private String sName;
    private String fullName;
    private String address;
    private String city;
    private String teleHome;
    private String email;
    private String dob;
    private String gender;
    private String nic;
    private String school;
    private String uni;
    private String faculty;
    private String teleMobile;
    private String higherEd;

    public StudentDet() {
    }

    public StudentDet(String sId, String sName, String fullName, String address, String city, String teleHome, String email, String dob, String gender, String nic, String school, String uni, String faculty, String teleMobile, String higherEd) {
        this.sId = sId;
        this.sName = sName;
        this.fullName = fullName;
        this.address = address;
        this.city = city;
        this.teleHome = teleHome;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
        this.nic = nic;
        this.school = school;
        this.uni = uni;
        this.faculty = faculty;
        this.teleMobile = teleMobile;
        this.higherEd = higherEd;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTeleHome() {
        return teleHome;
    }

    public void setTeleHome(String teleHome) {
        this.teleHome = teleHome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getUni() {
        return uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getTeleMobile() {
        return teleMobile;
    }

    public void setTeleMobile(String teleMobile) {
        this.teleMobile = teleMobile;
    }

    public String getHigherEd() {
        return higherEd;
    }

    public void setHigherEd(String higherEd) {
        this.higherEd = higherEd;
    }

    @Override
    public String toString() {
        return "StudentDet{" +
                "sId='" + sId + '\'' +
                ", sName='" + sName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", teleHome='" + teleHome + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", nic='" + nic + '\'' +
                ", school='" + school + '\'' +
                ", uni='" + uni + '\'' +
                ", faculty='" + faculty + '\'' +
                ", teleMobile='" + teleMobile + '\'' +
                ", higherEd='" + higherEd + '\'' +
                '}';
    }
}
