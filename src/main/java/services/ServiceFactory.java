package services;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ServiceFactory {
	private static ServiceFactory instance = null;
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("entrenamosuy");
	private ServiceFactory(){}
	
	public static ServiceFactory getInstance() {
		if (instance == null) {
			instance = new ServiceFactory();
		}
		return instance;
	}
	
	public ActivityService getActivityService() {
		return new ActivityService(entityManagerFactory.createEntityManager());
	}
	
	public ClassService getClassService() {
		return new ClassService(entityManagerFactory.createEntityManager());
	}
	
	public InstituteService getInstituteService() {
		return new InstituteService(entityManagerFactory.createEntityManager());
	}
	
	public UserService getUserService() {
		return new UserService(entityManagerFactory.createEntityManager());
	}
}
