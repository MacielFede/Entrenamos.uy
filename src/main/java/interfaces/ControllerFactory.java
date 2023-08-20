package interfaces;

import controllers.ClassController;
import controllers.InstituteController;
import controllers.UserController;

public class ControllerFactory {
	private static ControllerFactory instance = null;
	
	private ControllerFactory(){}
	
	public static ControllerFactory getInstance() {
		if (instance == null)
			instance = new ControllerFactory();
		return instance;
	}
	
	public UserInterface getUserInterface() {
		return new UserController();
	}
	
	public ClassInterface getClassInterface() {
		return new ClassController();
	}
	
	public InstituteInterface getInstituteInterface() {
		return new InstituteController();
	}
}
