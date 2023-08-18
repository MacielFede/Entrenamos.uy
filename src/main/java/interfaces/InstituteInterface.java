package interfaces;

import java.util.List;

import dataTypes.DtActivity;
import dataTypes.DtClass;
import dataTypes.DtSportActivity;
import dataTypes.DtSportInstitute;

public interface InstituteInterface {
	public List<DtSportInstitute> listSportInstitutes();
	public List<DtActivity> selectInstitution(String institutionName);
	public List<DtClass> chooseActivity(DtActivity activity);
	public List<DtSportActivity> listSportsActivitiesRanking();
	public boolean addNewSportActivity(DtSportActivity sportActivity, Integer idInstitute);
	public boolean modiFySportInstitute(String desc, String url);
	public boolean registerInstitution(String name, String description, String url);
	public DtSportActivity getActivity(String activityName);
	public DtSportInstitute chooseSportInstitute(String name);
}
