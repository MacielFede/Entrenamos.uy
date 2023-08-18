package handlers;

import java.util.List;

import dataTypes.DtUser;

public class UserService {
	private static UserService instance = null;
	
	private UserService(){}
	
	public static UserService getInstance() {
		if (instance == null)
			instance = new UserService();
		return instance;
	}
	
	public DtUser getUserByNickname(String nickname) {
		DtUser DtU = null;
		return DtU;
	}
	
	public DtUser getUserByEmail(String email) {
		DtUser DtU = null;
		return DtU;
	}
	
	public List<DtUser> getAllUsers() {
		List<DtUser> lDtU = null;
		return lDtU;
	}
}
