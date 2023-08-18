package dataTypes;

import java.util.Date;

public class DtUser {
	protected String nickname;
	protected String name;
	protected String lastName;
	protected String email;
	protected Date bornDate; 
	
	public DtUser(String nickname, String name, String lastName, String email, Date bornDate) {
		super();
		this.nickname = nickname;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.bornDate = bornDate;
	}

	public String getNickname() {
		return nickname;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public Date getBornDate() {
		return bornDate;
	}

}
