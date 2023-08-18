package interfaces;

import dataTypes.DtUser;

public interface UserInterface {
	public DtUser chooseUser(String email);
	public void updateUserInfo(DtUser updatedUser);
	public void newMember(DtUser DtNewUser);
}
