package interfaces;

import dataTypes.DtUser;
import dataTypes.DtClass;
import exceptions.AtributeAlreadyExists;
import exceptions.EmptyRequiredFieldException;
import exceptions.FebruaryDayException;
import exceptions.SameYearException;
import java.util.Map;

public interface UserInterface {
	DtUser chooseUser(String nickname);
	String [] listUsersByNickname();
	void updateUserInfo(DtUser updatedUser) throws EmptyRequiredFieldException, FebruaryDayException, SameYearException;
	void newMember(DtUser DtNewUser);
	public boolean existsNickname(String nickName);
	public boolean existsEmail(String email);
	public void newUser(DtUser newUser, String institute) throws EmptyRequiredFieldException, FebruaryDayException, SameYearException, AtributeAlreadyExists;
	public Map<String,DtClass> getMemberEnrolledClasses(String nickname);
  public void addEnrollment(String className, DtUser user, Float price) throws Exception;
	public String[] listMembersByNickname();
}



