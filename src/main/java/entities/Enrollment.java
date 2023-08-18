package entities;

import repository.EnrollmentId;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@IdClass(EnrollmentId.class)
public class Enrollment {
    // Here Enrollment has visibility over Class because of the hibernate way of defining associations
    @Id
    @ManyToOne
    private Member user;
    @Id
    @ManyToOne
    private Class aClass;
    private Float cost;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp enrollmentDate;

    public Enrollment() {
    }
    public Enrollment(Member user, Class aClass, Timestamp enrollmentDate, Float cost) {
        this.user = user;
        this.aClass = aClass;
        this.enrollmentDate = enrollmentDate;
        this.cost = cost;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }


    public Member getMember() {
        return user;
    }


    public void setMember(Member user) {
        this.user = user;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public Timestamp getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Timestamp enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
