package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dataTypes.DtActivity;
import dataTypes.DtClass;
import dataTypes.DtProfessor;
import dataTypes.DtUser;
import entities.Class;
import entities.Enrollment;
import entities.Member;
import entities.Professor;
import entities.User;
import repository.GenericRepository;

public class UserService {
	@PersistenceContext
	private EntityManager entityManager;
	private final GenericRepository<User> userRepository;
	private final GenericRepository<Professor> professorRepository;
	private final GenericRepository<Member> memberRepository;
	

	public UserService(EntityManager entityManagers) {
		this.entityManager = entityManagers;
		this.userRepository = new GenericRepository<User>(entityManager, User.class);
		this.professorRepository = new GenericRepository<Professor>(entityManager,Professor.class);
		this.memberRepository = new GenericRepository<Member>(entityManager,Member.class);
	}
	
	public Map<String,DtClass> getMemberClasses(String nickname) {
		Map<String, DtClass> classes = new TreeMap<String, DtClass>();
		String[] joinProperties = new String[]{"enrollments"};
		Member member = memberRepository.findById(nickname,"nickname",joinProperties);
		// If member exisists
		if (member != null) {
			// Get his enrollments
			List<Enrollment> enrollments = member.getEnrollments();
			
			// If he has enrollments
			if (enrollments != null) {
				// Add the related class to the Map
				for (Enrollment e: enrollments) {
					classes.put(e.getaClass().getName(),e.getaClass().getData());
				}
			}
		}
		entityManager.close();
		return classes;
	}

	public void addClassToProfessor(DtClass newClass, String professorNickname) {
		try {
			// Get class
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			ClassService classService = serviceFactory.getClassService();
			Class classToAdd = classService.getClassByName(newClass.getName());
			if (classToAdd == null) {
				throw new Exception("Class is null");
			}
			
			entityManager.getTransaction().begin();
			Professor professor = professorRepository.findById(professorNickname, "nickname");
			professor.getClasses().put(classToAdd.getName(), classToAdd);
			professorRepository.save(professor);
			entityManager.getTransaction().commit();
			entityManager.close();
		} catch (Exception e) {
			System.out.println(e);
			entityManager.getTransaction().rollback();
			entityManager.close();
		}
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
		for (User u : userRepository.findAll()) {
			lDtU.put(u.getNickname(), u.getData());
		}
		entityManager.close();
		return lDtU;
	}

	public void updateUser(DtUser userUpdated) {
		try {
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

	public String[] getAllEmails() {
		Map<String, DtUser> allUsers = this.getAllUsers();
		List<String> emails = new ArrayList<>();
		for (Map.Entry<String, DtUser> user : allUsers.entrySet()) {
			emails.add(user.getValue().getEmail());
		}
		return emails.toArray(new String[0]);
	}

	public void newUser(DtUser newUser) {
		entityManager.getTransaction().begin();

		if (newUser instanceof DtProfessor) {
			Professor newProfessor = new Professor(((DtProfessor) newUser).getDescription(),
					((DtProfessor) newUser).getBiography(), ((DtProfessor) newUser).getWebPage(), newUser.getNickname(),
					newUser.getName(), newUser.getLastName(), newUser.getEmail(), newUser.getBornDate());
			userRepository.save(newProfessor);
		} else {
			User newStudent = new Member(newUser.getNickname(), newUser.getName(), newUser.getLastName(),
					newUser.getEmail(), newUser.getBornDate());
			userRepository.save(newStudent);
		}
		entityManager.getTransaction().commit();

	}
}
