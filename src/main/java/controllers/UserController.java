package controllers;

import dataTypes.DtUser;
import interfaces.UserInterface;
import services.ServiceFactory;
import services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserController implements UserInterface {
	private final UserService userService = ServiceFactory.getInstance().getUserService();
	public UserController() {
		super();
	}
	
	@Override
	public DtUser chooseUser(String nickname) {
		DtUser dtU = null;
		return dtU;
	}

	@Override
	public String [] listUsersByNickname() {
		// Returns an array with all the user nicknames and the string "<Nicknames>"
		Map<String, DtUser> dtMap = userService.getAllUsers();
		List<String> nicknames = new ArrayList<>();
		nicknames.add("<Nicknames>");
		for(Map.Entry<String,DtUser> user : dtMap.entrySet()){
			nicknames.add(user.getKey());
		}
		return nicknames.toArray(new String[0]);
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
