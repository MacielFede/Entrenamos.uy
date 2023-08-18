package controllers;

import dataTypes.DtUser;
import interfaces.UserInterface;

public class UserController implements UserInterface {

	public UserController() {
		super();
	}
	
	@Override
	public DtUser chooseUser(String email) {
		DtUser dtU = null;
		return dtU;
	}

	@Override
	public void updateUserInfo(DtUser updatedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newMember(DtUser DtNewUser) {
		// TODO Auto-generated method stub
		
	}
}
