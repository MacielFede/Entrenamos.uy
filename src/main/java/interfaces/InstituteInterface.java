package interfaces;

import java.util.List;
import java.util.Map;

import dataTypes.DtActivity;
import dataTypes.DtClass;
import dataTypes.DtInstitute;
import dataTypes.DtUser;
import exceptions.EmptyRequiredFieldException;
import exceptions.NullPriceException;


public interface InstituteInterface {
	public boolean checkClassNameAvailability(String className);
	public boolean checkActivityAvialability(String name);
	public DtClass chooseClassByName(String className);
	public List<DtClass> listClassesDictationRanking();
	public void createSportClass(DtClass newClass, String idSportActivity, String idProfessor);
	public Map<String, DtInstitute> listSportInstitutes();
	public Map<String, DtActivity> selectInstitution(String institutionName);
	public Map<String, DtClass> chooseActivity(String activity);
	public List<DtActivity> listSportsActivitiesRanking();
	public void modiFySportInstitute(DtInstitute institute);
	public void addNewSportActivity(DtActivity sportActivity, String nameInstitute);
	public void registerInstitution(String name, String description, String url);
	public DtActivity getActivity(String activityName);
	void updateActivityInfo(DtActivity dtA) throws EmptyRequiredFieldException, NullPriceException;
	public DtInstitute chooseSportInstitute(String name);
	public boolean checkInstitutionAvialability(String name);
    String[] listSportActivitiesByName();
    public Map<String,DtActivity> getAllActivities();
	DtActivity chooseActivityByName(String activity);
}
