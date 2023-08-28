package interfaces;

import dataTypes.DtUser;
import exceptions.AtributeAlreadyExists;
import exceptions.EmptyRequiredFieldException;
import exceptions.FebruaryDayException;
import exceptions.SameYearException;

import java.util.List;

public interface UserInterface {
	DtUser chooseUser(String nickname);

	String [] listUsersByNickname();
	void updateUserInfo(DtUser updatedUser) throws EmptyRequiredFieldException, FebruaryDayException, SameYearException;
	void newMember(DtUser DtNewUser);
	public boolean existsNickname(String nickName);
	public boolean existsEmail(String email);
	public void newUser(DtUser newUser) throws EmptyRequiredFieldException, FebruaryDayException, SameYearException, AtributeAlreadyExists;

	}
