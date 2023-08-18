package handlers;

import java.util.List;

import dataTypes.DtSportInstitute;

public class ActivityService {
	private static ActivityService instance = null;
	
	private ActivityService(){}
	
	public static ActivityService getInstance() {
		if (instance == null)
			instance = new ActivityService();
		return instance;
	}
	
	public DtSportInstitute getActivityByName(String name) {
		DtSportInstitute dti = null;
		return dti;
	}
	
	public List<DtSportInstitute> getAllActivity() {
		List<DtSportInstitute> l = null;
		return l;
	}
}
