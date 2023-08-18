package entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Map;

@Entity
public class Class {

    @Id
    private String name;
    // I put date and startTime together because Timestamp already contains date and time
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp dateAndTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp registerDate;
    private String url;
    @OneToMany(mappedBy = "aClass",cascade = CascadeType.ALL)
    // The String in the map represent the users id.
    private Map<String, Enrollment> enrollments;

    public Class() {}

    public Map<String, Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Map<String, Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public Class(String name, Timestamp dateAndTime, Timestamp registerDate, String url, Map<String, Enrollment> enrollments) {
        this.name = name;
        this.dateAndTime = dateAndTime;
        this.registerDate = registerDate;
        this.url = url;
        this.enrollments = enrollments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Timestamp dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void createEnrollment(User user) {}

    public DtClass getData(){ return new DtClass(this.name, this.url, this.dateAndTime, this.registerDate); }
}
