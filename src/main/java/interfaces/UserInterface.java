package interfaces;

import dataTypes.DtUser;

import java.util.List;

public interface UserInterface {
	DtUser chooseUser(String email);

	String [] listUsersByNickname();
	void updateUserInfo(DtUser updatedUser);
	void newMember(DtUser DtNewUser);
}
