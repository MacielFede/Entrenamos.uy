package dataTypes;

import java.sql.Timestamp;

public class DtMember extends DtUser{
	//private Map<String, DtClass> relatedClasses;
	public DtMember(/* Map<String, DtClass> relatedClasses ,*/ String nickname, String name, String lastName, String email, Timestamp bornDate) {
		super(nickname, name, lastName, email, bornDate);
		/*this.relatedClasses = relatedClasses;*/
	}
	/*
	public Map<String, DtClass> getRelatedClasses() {
		return relatedClasses;
	}*/
}
