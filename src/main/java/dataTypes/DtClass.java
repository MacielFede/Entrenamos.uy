package dataTypes;


import java.util.Date;
import java.util.Map;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtClass {

    private final String name;
    private final Date dateAndTime;
    private final Date registerDate;
    private final String url;
    private final String imgName;
    private final int enrollmentsQuantity;
	// The String in the map represent the users id.
    private final Map<String, DtEnrollment> enrollments;

    public DtClass(String name, Date dateAndTime, Date registerDate, String url, int enrollmentsQuantity, Map<String, DtEnrollment> enrollments, String imgName) {
        this.name = name;
        this.dateAndTime = dateAndTime;
        this.registerDate = registerDate;
        this.url = url;
        this.enrollmentsQuantity = enrollmentsQuantity;
        this.enrollments = enrollments;
        this.imgName = imgName;
    }
    
    public String getImgName() {
    	return imgName;
    }

    public String getName() {
        return name;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public String getUrl() {
        return url;
    }
    
    public int getEnrollmentsQuantity() {
    	return enrollmentsQuantity;
    }
    
    public Map<String, DtEnrollment> getEnrollments() {
        return enrollments;
    }
}
