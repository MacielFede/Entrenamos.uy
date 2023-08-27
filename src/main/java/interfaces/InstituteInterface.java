package interfaces;

import java.util.List;
import java.util.Map;

import dataTypes.DtActivity;
import dataTypes.DtClass;
import dataTypes.DtInstitute;


public interface InstituteInterface {
	public Map<String, DtInstitute> listSportInstitutes();
	public Map<String, DtActivity> selectInstitution(String institutionName);
	public List<DtActivity> listSportsActivitiesRanking();
	public boolean addNewSportActivity(DtActivity sportActivity, Integer idInstitute);
	public boolean modiFySportInstitute(String desc, String url);
	public boolean registerInstitution(String name, String description, String url);
	public DtActivity getActivity(String activityName);

	void updateActivityInfo(DtActivity dtA);

	public DtInstitute chooseSportInstitute(String name);

    String[] listSportActivitiesByName();
}
