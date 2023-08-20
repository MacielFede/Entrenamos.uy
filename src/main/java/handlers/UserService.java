package handlers;

import java.util.Map;

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
	
	public Map<String, DtUser> getAllUsers() {
		Map<String, DtUser> lDtU = null;
		return lDtU;
	}
}