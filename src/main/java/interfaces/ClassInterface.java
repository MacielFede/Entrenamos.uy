package interfaces;

import java.util.Map;

import dataTypes.DtClass;
import dataTypes.DtUser;

public interface ClassInterface {
	public boolean registerUserToClass(DtClass rclass, DtUser user);
	public DtClass showClassInfo(String className);
	public Map<String, DtClass> listClassesBySportActivity(Integer idSportActivity);
	public DtClass chooseClassByName(String className);
	public Map<String, DtClass> listClassesDictationRanking();
	public boolean createSportClass(DtClass newClass, Integer idSportActivity);
}
