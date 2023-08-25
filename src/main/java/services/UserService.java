package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dataTypes.DtUser;
import entities.Activity;
import entities.Member;
import entities.Professor;
import entities.User;
import repository.GenericRepository;

public class UserService {
	@PersistenceContext
    private EntityManager entityManager;
	private final GenericRepository<User> userRepository;
	
	public UserService(EntityManager entityManagers){
		this.entityManager = entityManagers;
		this.userRepository = new GenericRepository<User>(entityManager, User.class);
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
		Map<String, DtUser> lDtU = new TreeMap<>();
		for(User u : userRepository.findAll()){
			lDtU.put(u.getNickname(), u.getData());
		}
		return lDtU;
	}

	public void updateUser(DtUser userUpdated) {
		entityManager.getTransaction().begin();
		if (userRepository.findById(userUpdated.getNickname(), "nickname") instanceof Professor){
			Professor updateProf = new Professor();
			updateProf.setEmail(userUpdated.getEmail());
			updateProf.setNickname(userUpdated.getNickname());
			updateProf.setName(userUpdated.getName());
			updateProf.setBornDate(userUpdated.getBornDate());
			updateProf.setLastName(userUpdated.getLastName());
			userRepository.update(updateProf);
		} else {
			Member updateMember = new Member();
			updateMember.setEmail(userUpdated.getEmail());
			updateMember.setNickname(userUpdated.getNickname());
			updateMember.setName(userUpdated.getName());
			updateMember.setBornDate(userUpdated.getBornDate());
			updateMember.setLastName(userUpdated.getLastName());
			userRepository.update(updateMember);
		}
		entityManager.getTransaction().commit();
	}
}
