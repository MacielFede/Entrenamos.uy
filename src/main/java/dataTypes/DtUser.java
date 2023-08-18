package dataTypes;

import java.sql.Timestamp;

public class DtUser {
	protected String nickname;
	protected String name;
	protected String lastName;
	protected String email;
	protected Timestamp bornDate; 
	
	public DtUser(String nickname, String name, String lastName, String email, Timestamp bornDate) {
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

	public Timestamp getBornDate() {
		return bornDate;
	}

}
