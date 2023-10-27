package dataTypes;

import java.util.Date;
import java.util.Map;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtMember extends DtUser {
	private Map<String, DtClass> relatedClasses;

	
	public DtMember() {
	}
	
	public DtMember(Map<String, DtClass> relatedClasses, String nickname, String name, String lastName, String email,
					Date bornDate, String password) {
		super(nickname, name, lastName, email, bornDate, password);
		this.relatedClasses = relatedClasses;
	}

	public Map<String, DtClass> getRelatedClasses() {
		return relatedClasses;
	}
}
