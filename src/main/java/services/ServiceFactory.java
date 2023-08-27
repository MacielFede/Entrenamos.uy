package services;

import repository.Connection;

public class ServiceFactory {
	private static ServiceFactory instance = null;
	private static Connection databaseConnection;
	private ServiceFactory(){}
	
	public static ServiceFactory getInstance() {
		if (instance == null) {
			instance = new ServiceFactory();
			databaseConnection = Connection.getInstance();
		}
		return instance;
	}
	
	public ActivityService getActivityService() {
		return new ActivityService(databaseConnection.getEntityManager());
	}
	
	public ClassService getClassService() {
		return new ClassService(databaseConnection.getEntityManager());
	}
	
	public InstituteService getInstituteService() {
		return new InstituteService(databaseConnection.getEntityManager());
	}
	
	public UserService getUserService() {
		return new UserService(databaseConnection.getEntityManager());
	}
}
