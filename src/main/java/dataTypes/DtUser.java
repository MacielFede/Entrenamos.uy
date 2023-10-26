package dataTypes;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtUser {
	protected final String nickname;
	protected final  String name;
	protected final  String lastName;
	protected final String email;
	protected final Date bornDate;
	protected final String password;

	public DtUser(String nickname, String name, String lastName, String email, Date bornDate, String password) {
		super();
		this.nickname = nickname;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.bornDate = bornDate;
		this.password = password;
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

	public Date getBornDate() { return bornDate; }



	public String getPassword() {
		return password;
	}


}
