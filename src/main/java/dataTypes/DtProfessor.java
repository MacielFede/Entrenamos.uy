package dataTypes;

import java.sql.Timestamp;

public class DtProfessor extends DtUser{
	//private Map<String, DtClass> relatedClasses;
	private String description;
	private String biography;
	private String webPage;
	
	public DtProfessor(
			/* Map<String, DtClass> relatedClasses ,*/ 
			String description, 
			String biography, 
			String webPage, 
			String nickname, 
			String name, 
			String lastName, 
			String email, 
			Timestamp bornDate) {
		super(nickname, name, lastName, email, bornDate);
		/*this.relatedClasses = relatedClasses;*/
		this.description = description;
		this.biography = biography;
		this.webPage = webPage;
	}
	
	public String getDescription() {
		return description;
	}

	public String getBiography() {
		return biography;
	}

	public String getWebPage() {
		return webPage;
	}
	/*
	public Map<String, DtClass> getRelatedClasses() {
		return relatedClasses;
	}*/
}
