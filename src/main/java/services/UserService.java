package services;

import java.security.Provider;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dataTypes.DtClass;
import dataTypes.DtProfessor;
import dataTypes.DtUser;
import entities.Class;
import dataTypes.DtEnrollment;
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

	public void newUser(DtUser newUser, String institute) {
		entityManager.getTransaction().begin();

		if (newUser instanceof DtProfessor) {
			Professor newProfessor = new Professor(((DtProfessor) newUser).getDescription(),
					((DtProfessor) newUser).getBiography(), ((DtProfessor) newUser).getWebPage(), newUser.getNickname(),
					newUser.getName(), newUser.getLastName(), newUser.getEmail(), newUser.getBornDate());
			newProfessor.setSportInstitution(ServiceFactory.getInstance().getInstituteService().addProffesorToInstitute(institute, newProfessor));
			userRepository.save(newProfessor);
		} else {
			User newStudent = new Member(newUser.getNickname(), newUser.getName(), newUser.getLastName(),
					newUser.getEmail(), newUser.getBornDate());
			userRepository.save(newStudent);
		}
		entityManager.getTransaction().commit();
		entityManager.close();
	}

    public void addEnrollment(DtEnrollment enrollment, String className) {
		// Crear la entidad enrollment con la clase de nombre className y persistirlo
		Enrollment newEnrollment = new Enrollment();
		GenericRepository<Member> memberRepo = new GenericRepository<>(entityManager, Member.class);
		Member member = memberRepo.findById(enrollment.getMember().getNickname(), "nickname", new String[]{"enrollments"});
		newEnrollment.setEnrollmentDate(enrollment.getEnrollmentDate());
		newEnrollment.setCost(enrollment.getCost());
		newEnrollment.setMember(member);
		newEnrollment.setaClass(ServiceFactory.getInstance().getClassService().addEnrollmentToClass(className, newEnrollment));
		List<Enrollment> newEnrollments = member.getEnrollments();
		newEnrollments.add(newEnrollment);
		member.setEnrollments(newEnrollments);
		entityManager.getTransaction().begin();
		userRepository.save(member);
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
