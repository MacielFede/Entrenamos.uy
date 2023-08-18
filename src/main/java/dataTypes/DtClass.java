package dataTypes;

import entities.Enrollment;

import java.sql.Timestamp;
import java.util.Map;

public class DtClass {

    private String name;
    private Timestamp dateAndTime;
    private Timestamp registerDate;
    private String url;
    // The String in the map represent the users id.
    private Map<String, DtEnrollment> enrollments;

    public DtClass(String name, Timestamp dateAndTime, Timestamp registerDate, String url, Map<String, DtEnrollment> enrollments) {
        this.name = name;
        this.dateAndTime = dateAndTime;
        this.registerDate = registerDate;
        this.url = url;
        this.enrollments = enrollments;
    }

    public String getName() {
        return name;
    }

    public Timestamp getDateAndTime() {
        return dateAndTime;
    }

    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, DtEnrollment> getEnrollments() {
        return enrollments;
    }
}
