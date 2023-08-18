package interfaces;

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
}
