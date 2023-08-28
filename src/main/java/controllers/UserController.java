package controllers;

import dataTypes.DtUser;
import exceptions.EmptyRequiredFieldException;
import exceptions.FebruaryDayException;
import exceptions.SameYearException;
import interfaces.UserInterface;
import services.ServiceFactory;
import services.UserService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class UserController implements UserInterface {
	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private Map<String, DtUser> cachedUsers = null;
	public UserController() {
		super();
	}
	
	@Override
	public DtUser chooseUser(String nickname) {
		if (cachedUsers == null){
			cachedUsers = serviceFactory.getUserService().getAllUsers();
		}
		return cachedUsers.get(nickname);
	}

	@Override
	public String [] listUsersByNickname() {
		// Returns an array with all the user nicknames and the string "<Nicknames>"
		// Also renovates the cached array
		cachedUsers = serviceFactory.getUserService().getAllUsers();
		List<String> nicknames = new ArrayList<>();
		nicknames.add("<Nicknames>");
		for(Map.Entry<String,DtUser> user : cachedUsers.entrySet()){
			nicknames.add(user.getKey());
		}
		return nicknames.toArray(new String[0]);
	}

	@Override
	public void updateUserInfo(DtUser updatedUser) throws EmptyRequiredFieldException, FebruaryDayException, SameYearException {
		if(updatedUser.getBornDate().getYear() >= Calendar.getInstance().getWeekYear()){
			throw new SameYearException();
		}
		if(updatedUser.getBornDate().getMonth() == 2 && Integer.parseInt(updatedUser.getBornDate().toString().substring(8,10)) > 28){
			throw new FebruaryDayException();
		}
		if(updatedUser.getEmail().isEmpty() || updatedUser.getNickname().isEmpty()){
			String blankField = updatedUser.getEmail().isEmpty() ? updatedUser.getEmail() : updatedUser.getNickname();
			throw new EmptyRequiredFieldException(blankField);
		}
		serviceFactory.getUserService().updateUser(updatedUser);
		cachedUsers.put(updatedUser.getNickname(), updatedUser);
	}

	@Override
	public void newMember(DtUser DtNewUser) {
		// TODO Auto-generated method stub
		
	}
}
