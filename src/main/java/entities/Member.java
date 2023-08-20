package entities;

import java.util.*;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import dataTypes.DtClass;
import dataTypes.DtMember;
import dataTypes.DtUser;

@Entity
public class Member extends User {
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Enrollment> enrollments;

	public Member() {
		super();
	}

	public Member(String nickname, String name, String lastName, String email, Date bornDate) {
		super(nickname, name, lastName, email, bornDate);
		this.enrollments = new ArrayList<Enrollment>();
	}

	public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	@Override
	public DtUser getData() {
		Map<String,DtClass> xd = new TreeMap<String,DtClass>();
		return new DtMember(xd,this.nickname, this.name, this.lastName, this.email, this.bornDate);
	}

	@Override
	public Map<String, DtClass> getRelatedClasses() {
		Map<String,DtClass> xd = new TreeMap<String,DtClass>();
		return xd;
	}

}
