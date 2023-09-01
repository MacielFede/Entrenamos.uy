package controllers;

import dataTypes.DtEnrollment;
import dataTypes.DtUser;
import entities.Member;
import exceptions.*;
import interfaces.ControllerFactory;
import interfaces.UserInterface;
import services.ServiceFactory;
import services.UserService;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.*;

public class UserController implements UserInterface {
	private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private final ControllerFactory controllerFactory = ControllerFactory.getInstance();
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

	public boolean existsNickname(String nickName) {
		String[] allNickname = listUsersByNickname();
		for (String u : allNickname) {
			if (u.equals(nickName))
				return true;
		}
		return false;
	}
	public boolean existsEmail(String email) {
		String[] allEmail = serviceFactory.getUserService().getAllEmails();
		for (String u : allEmail) {
			if (u.equals(email))
				return true;
		}
		return false;

	}
	public void newUser(DtUser newUser) throws EmptyRequiredFieldException, FebruaryDayException, SameYearException, AtributeAlreadyExists {
		if (newUser.getBornDate().getYear() >= Calendar.getInstance().getWeekYear())
			throw new SameYearException();

		if (newUser.getBornDate().getMonth() == 2 && Integer.parseInt(newUser.getBornDate().toString().substring(8, 10)) > 28)
			throw new FebruaryDayException();

		if (newUser.getEmail().isEmpty() || newUser.getNickname().isEmpty()) {
			String blankField = newUser.getEmail().isEmpty() ? newUser.getEmail() : newUser.getNickname();
			throw new EmptyRequiredFieldException(blankField);
		}
		if (existsNickname(newUser.getNickname()))
			throw new AtributeAlreadyExists((String) "Nickname", newUser.getNickname());

		if (existsEmail(newUser.getEmail()))
			throw new AtributeAlreadyExists("Email", newUser.getEmail());

		serviceFactory.getUserService().newUser(newUser);
	}

	public void addEnrollment(String className, DtUser user, Float price) throws Exception {
		if (!serviceFactory.getUserService().userExists(user.getNickname())) {
			throw new Exception("El usuario indicado no existe, seleccione uno valido");
		}
		if (!serviceFactory.getClassService().classExists(className)){
			throw new ClassNotFoundException("La clase indicada no existe, seleccione una valida por favor");
		}
		if (serviceFactory.getUserService().userAlreadySignedUpToClass(user.getNickname(), className)){
			throw new UserAlreadySignedUpToClassException();
		}
		ln("usuario no esta enrolled");
		DtEnrollment enrollment = new DtEnrollment(user, price, Calendar.getInstance().getTime());
		serviceFactory.getUserService().addEnrollment(enrollment, className);
	}

	@Override
	public String[] listMembersByNickname() {
		List<String> nicknames = new ArrayList<>();
		for(Map.Entry<String,DtUser> user : serviceFactory.getUserService().getMembers().entrySet()){
			nicknames.add(user.getKey());
		}
		return nicknames.toArray(new String[0]);
	}
}
