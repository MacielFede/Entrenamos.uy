package dataTypes;

import java.sql.Timestamp;

public class DtEnrollment {
    // The DtEnrollment hasn't visibility over Class. The visibility is Class -> Enrollment -> Member
    private DtMember user;

    private Float cost;
    private Timestamp enrollmentDate;

    public DtEnrollment(DtMember user, Float cost, Timestamp enrollmentDate) {
        this.user = user;
        this.cost = cost;
        this.enrollmentDate = enrollmentDate;
    }

    public DtMember getMember() {
        return user;
    }

    public Float getCost() {
        return cost;
    }

    public Timestamp getEnrollmentDate() {
        return enrollmentDate;
    }
}
