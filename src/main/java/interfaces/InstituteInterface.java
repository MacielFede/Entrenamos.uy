package interfaces;

import java.util.Map;

import dataTypes.DtActivity;
import dataTypes.DtClass;
import dataTypes.DtSportActivity;
import dataTypes.DtSportInstitute;

public interface InstituteInterface {
	public Map<String, DtSportInstitute> listSportInstitutes();
	public Map<String, DtActivity> selectInstitution(String institutionName);
	public Map<String, DtClass> chooseActivity(DtActivity activity);
	public Map<String, DtSportActivity> listSportsActivitiesRanking();
	public boolean addNewSportActivity(DtSportActivity sportActivity, Integer idInstitute);
	public boolean modiFySportInstitute(String desc, String url);
	public boolean registerInstitution(String name, String description, String url);
	public DtSportActivity getActivity(String activityName);
	public DtSportInstitute chooseSportInstitute(String name);
}
