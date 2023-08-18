package entities;

import dataTypes.DtEnrollment;
import repository.EnrollmentId;

import javax.persistence.*;
import java.util.Date;

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
    private Date enrollmentDate;

    public Enrollment() {
    }
    public Enrollment(Member user, Class aClass, Date enrollmentDate, Float cost) {
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

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public DtEnrollment getData() {
        return new DtEnrollment(this.user.getData(), this.cost, this.enrollmentDate);
    }
}
