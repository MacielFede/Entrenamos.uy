package dataTypes;

import java.util.Date;

public class DtEnrollment {
    // The DtEnrollment hasn't visibility over Class. The visibility is Class -> Enrollment -> Member
    private final DtUser user;

    private final Float cost;
    private final Date enrollmentDate;

    public DtEnrollment(DtUser user, Float cost, Date enrollmentDate) {
        this.user = user;
        this.cost = cost;
        this.enrollmentDate = enrollmentDate;
    }

    public DtUser getMember() {
        return user;
    }

    public Float getCost() {
        return cost;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }
    
}
