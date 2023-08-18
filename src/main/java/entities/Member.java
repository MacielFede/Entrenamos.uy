package entities;

import java.sql.Timestamp;
//import java.util.*;

import javax.persistence.Entity;

import dataTypes.DtMember;
import dataTypes.DtUser;

@Entity
public class Member extends User{
	//private List<Enrollment> enrollments;
	
	public Member() {
		super();
	}
	
	public Member(String nickname, String name, String surname, String email, Timestamp bornDate) {
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
