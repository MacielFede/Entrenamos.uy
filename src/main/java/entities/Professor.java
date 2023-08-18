package entities;

import java.util.Date;

import javax.persistence.Entity;

import dataTypes.DtProfessor;
import dataTypes.DtUser;

@Entity
public class Professor extends User{
	//private Map<String, SportInstitution> sportInstitutions;
	private String description;
	private String biography;
	private String webPage;
	
	public Professor() {
		super();
	}
	
	public Professor(String description, String biography, String webPage, String nickname, String name, String surname, String email, Date bornDate) {
		super(nickname, name, surname, email, bornDate);
		//this.sportInstitutions = new TreeMap<String, SportInstitution>();
		this.description = description;
		this.biography = biography;
		this.webPage = webPage;
	}
	
	/*
	public Map<String, SportInstitution> getSportInstitutions() {
		return sportInstitutions;
	}

	public void setSportInstitutions(Map<String, SportInstitution> sportInstitutions) {
		this.sportInstitutions = sportInstitutions;
	}
	*/
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getWebPage() {
		return webPage;
	}

	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}


	@Override
	public DtUser getData() {
		// TODO Auto-generated method stub
		return new DtProfessor(/* relatedClasses, */this.description, this.biography, this.webPage, this.nickname, this.name, this.lastName, this.email, this.bornDate);
	}

}
