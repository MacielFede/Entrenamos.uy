package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dataTypes.DtUser;

public class UserService {
	@PersistenceContext
    private EntityManager entityManager;
	
	public UserService(EntityManager entityManagers){
		this.entityManager = entityManager;
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

	public String[] getAllUsersNickname(){
		List<String> nicknames = new ArrayList<>();
		return nicknames.toArray(new String[0]);
	}
}
