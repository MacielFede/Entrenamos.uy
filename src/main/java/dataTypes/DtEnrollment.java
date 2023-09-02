package dataTypes;

import java.util.Date;

public class DtEnrollment {
    // The DtEnrollment hasn't visibility over Class. The visibility is Class -> Enrollment -> Member
    private final DtUser user;
    private final DtClass aClass;

    private final Float cost;
    private final Date enrollmentDate;

    public DtEnrollment(DtUser user, Float cost, Date enrollmentDate, DtClass aClass) {
        this.user = user;
        this.cost = cost;
        this.enrollmentDate = enrollmentDate;
        this.aClass = aClass;
    }

    public DtUser getMember() {
        return user;
    }
    
    public DtClass getAClass() {
    	return aClass;
    }

    public Float getCost() {
        return cost;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }
    
}
