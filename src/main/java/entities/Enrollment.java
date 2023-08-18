package entities;

import repository.EnrollmentId;

import javax.persistence.*;
import java.sql.Date;

@Entity
@IdClass(EnrollmentId.class)
public class Enrollment {
    @Id
    @ManyToOne
    private User user;

    @Id
    @ManyToOne
    private Class aClass;

    @Temporal(TemporalType.TIMESTAMP)
    private Date enrollmentDate;

    public Enrollment() {
    }

    public Enrollment(User user, Class aClass, Date enrollmentDate) {
        this.user = user;
        this.aClass = aClass;
        this.enrollmentDate = enrollmentDate;
    }

    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
