package entities;

//import java.util.*;
import java.util.Date;

import javax.persistence.Entity;

import dataTypes.DtMember;
import dataTypes.DtUser;

@Entity
public class Member extends User{
	//private List<Enrollment> enrollments;
	
	public Member() {
		super();
	}
	
	public Member(String nickname, String name, String surname, String email, Date bornDate) {
		super(nickname, name, surname, email, bornDate);
		//this.enrollments = new TreeList<Enrollment>();
	}
	/*
	public List<Enrollment> getEnrollments() {
		return enrollments;
	}
	
	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
	*/
	
	@Override
	public DtUser getData() {
		return new DtMember(/* relatedClasses, */this.nickname, this.name, this.lastName, this.email, this.bornDate);
	}
	/*
	@Override
	public Map<String, DtClass> getRelatedClasses(){
	}
	*/
}
