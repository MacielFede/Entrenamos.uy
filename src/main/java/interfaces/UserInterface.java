package interfaces;

import dataTypes.DtUser;
import exceptions.EmptyRequiredFieldException;
import exceptions.FebruaryDayException;
import exceptions.SameYearException;

import java.util.List;

public interface UserInterface {
	DtUser chooseUser(String nickname);

	String [] listUsersByNickname();
	void updateUserInfo(DtUser updatedUser) throws EmptyRequiredFieldException, FebruaryDayException, SameYearException;
	void newMember(DtUser DtNewUser);
}
