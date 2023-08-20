package handlers;

import java.util.Map;

import dataTypes.DtActivity;

public class ActivityService {
	private static ActivityService instance = null;
	
	private ActivityService(){}
	
	public static ActivityService getInstance() {
		if (instance == null)
			instance = new ActivityService();
		return instance;
	}
	
	public DtActivity getActivityByName(String name) {
		DtActivity dti = null;
		return dti;
	}
	
	public Map<String, DtActivity> getAllActivity() {
		Map<String, DtActivity> l = null;
		return l;
	}
}
