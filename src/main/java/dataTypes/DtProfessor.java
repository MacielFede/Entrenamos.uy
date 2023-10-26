package dataTypes;

import java.util.Date;
import java.util.Map;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtProfessor extends DtUser {
	private Map<String, DtClass> relatedClasses;
	private final String description;
	private final String biography;
	private final String webPage;

	public DtProfessor(Map<String, DtClass> relatedClasses, String description, String biography, String webPage,
			String nickname, String name, String lastName, String email, Date bornDate, String password) {
		super(nickname, name, lastName, email, bornDate, password);
		this.relatedClasses = relatedClasses;
		this.description = description;
		this.biography = biography;
		this.webPage = webPage;
	}

	public DtProfessor(String description, String biography, String webPage, String nickname, String name, String lastname, String email, Date bornDate, String password) {
		super(nickname, name, lastname, email, bornDate, password);
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

	public Map<String, DtClass> getRelatedClasses() {
		return relatedClasses;
	}

}
