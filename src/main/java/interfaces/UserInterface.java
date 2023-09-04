package interfaces;

import dataTypes.DtUser;
import exceptions.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;

public interface UserInterface {
	DtUser chooseUser(String nickname);

	String [] listUsersByNickname();
	void updateUserInfo(DtUser updatedUser) throws EmptyRequiredFieldException, FebruaryDayException, SameYearException;
	void newMember(DtUser DtNewUser);
	public boolean existsNickname(String nickName);
	public boolean existsEmail(String email);
	public void newUser(DtUser newUser) throws EmptyRequiredFieldException, FebruaryDayException, SameYearException, AtributeAlreadyExists;
    public void addEnrollment(String className, DtUser user, Float price) throws Exception;

	public String[] listMembersByNickname();
}
