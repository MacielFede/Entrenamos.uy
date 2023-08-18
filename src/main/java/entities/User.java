package entities;

import java.util.Date;
import javax.persistence.*;

import dataTypes.DtUser;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
	@Id
	protected String nickname;
	protected String name;
	protected String lastName;
	protected String email;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date bornDate;
	
	public User() {
		super();
	}
	
	public User(String nickname, String name, String lastName, String email, Date bornDate) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.bornDate = bornDate;
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBornDate() {
		return bornDate;
	}
	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}
	
	public void updateInfo(DtUser user) {
		this.name = user.getName();
		this.lastName = user.getLastName();
		this.bornDate = user.getBornDate();
	}
	
	public abstract DtUser getData();
	
	//public abstract Map<String, DtClass> getRelatedClasses();
}