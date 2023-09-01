package services;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dataTypes.DtEnrollment;
import dataTypes.DtProfessor;
import dataTypes.DtUser;
import entities.Enrollment;
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
		entityManager.close();
		return lDtU;
	}

	public void updateUser(DtUser userUpdated) {
		try{
			entityManager.getTransaction().begin();
			User updatedUser = userRepository.findById(userUpdated.getNickname(), "nickname");
			updatedUser.setName(userUpdated.getName());
			updatedUser.setBornDate(userUpdated.getBornDate());
			updatedUser.setLastName(userUpdated.getLastName());
			userRepository.update(updatedUser);
			entityManager.getTransaction().commit();
			entityManager.close();
		} catch (Exception e) {
			entityManager.close();
		}
	}

	public String[] getAllEmails(){
		Map<String, DtUser> allUsers = this.getAllUsers();
		List<String> emails = new ArrayList<>();
		for (Map.Entry<String, DtUser> user : allUsers.entrySet()) {
			emails.add(user.getValue().getEmail());
		}
		return emails.toArray(new String[0]);
	}

	public void newUser(DtUser newUser){
		entityManager.getTransaction().begin();

		if(newUser instanceof DtProfessor){
			Professor newProfessor	= new Professor(
					((DtProfessor) newUser).getDescription(),
					((DtProfessor) newUser).getBiography(),
					((DtProfessor) newUser).getWebPage(),
					newUser.getNickname(), newUser.getName(),
					newUser.getLastName(), newUser.getEmail(),
					newUser.getBornDate());
			userRepository.save(newProfessor);
		}else{
			User newStudent = new Member(newUser.getNickname(), newUser.getName(), newUser.getLastName(), newUser.getEmail(), newUser.getBornDate());
			userRepository.save(newStudent);
		}
		entityManager.getTransaction().commit();

	}

    public void addEnrollment(DtEnrollment enrollment, String className) {
		// Crear la entidad enrollment con la clase de nombre className y persistirlo
		Enrollment newEnrollment = new Enrollment();
		newEnrollment.setEnrollmentDate(enrollment.getEnrollmentDate());
		newEnrollment.setCost(enrollment.getCost());
		newEnrollment.setaClass(ServiceFactory.getInstance().getClassService().getClassEntityByName(className));
		newEnrollment.setMember(userRepository.findById(enrollment.getMember().getNickname(), "nickname"));
		GenericRepository<Enrollment> enrollRepo = new GenericRepository<>(entityManager, Enrollment.class);
		entityManager.getTransaction().begin();
		enrollRepo.save(newEnrollment);
		entityManager.getTransaction().commit();
		entityManager.close();
    }

	public boolean userExists(String userNickname) {
		try{
			userRepository.findById(userNickname, "nickname");
			entityManager.close();
			return true;
		}catch(Exception e){
			entityManager.close();
			return false;
		}
	}

	public boolean userAlreadySignedUpToClass(String user, String className) {
		GenericRepository<Member> memberRepo = new GenericRepository<>(entityManager, Member.class);
		Member member = memberRepo.findById(user, "nickname", new String[]{"enrollments"});
		if(member.getEnrollments() == null || member.getEnrollments().isEmpty()){ return false; }
		for (Enrollment enrollment : member.getEnrollments()) {
			if (Objects.equals(enrollment.getaClass().getName(), className)) {
				entityManager.close();
				return true;
			}
		}
		entityManager.close();
		return false;
	}

	public Map<String, DtUser> getMembers() {
		GenericRepository<Member> memberRepo = new GenericRepository<>(entityManager, Member.class);
		Map<String, DtUser> lDtU = new TreeMap<>();
		for(Member u : memberRepo.findAll()){
			lDtU.put(u.getNickname(), u.getData());
		}
		entityManager.close();
		return lDtU;
	}
}
