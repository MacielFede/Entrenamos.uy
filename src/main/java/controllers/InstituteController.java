package controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import dataTypes.DtActivity;
import dataTypes.DtInstitute;
import interfaces.InstituteInterface;
import services.ActivityService;
import services.ServiceFactory;

public class InstituteController implements InstituteInterface {

	private Map<String, DtActivity> cachedActivities;
	public InstituteController() {
		super();
	}
	
	@Override
	public Map<String, DtInstitute> listSportInstitutes() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Map<String, DtActivity> selectInstitution(String institutionName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DtActivity> listSportsActivitiesRanking() {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ActivityService activityService = serviceFactory.getActivityService();
		List<DtActivity> activities = new ArrayList<DtActivity>();
		for(DtActivity dt : activityService.getAllActivity().values()) {
			activities.add(dt);
		}
		activities.sort(Comparator.comparingInt(DtActivity::getClassesQuantity).reversed());
		return activities;
	}

	@Override
	public boolean addNewSportActivity(DtActivity sportActivity, Integer idInstitute) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modiFySportInstitute(String desc, String url) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registerInstitution(String name, String description, String url) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DtActivity getActivity(String activityName) {

		return null;
	}

	@Override
	public void updateActivityInfo(DtActivity dtA){

	}

	@Override
	public DtInstitute chooseSportInstitute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] listSportActivitiesByName() {
		// Returns an array with all the activity names and the string "<Nombres>"
		// Also renovates the cached map
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ActivityService activityService = serviceFactory.getActivityService();
		cachedActivities = activityService.getAllActivity();
		List<String> names = new ArrayList<>();
		names.add("<Nombres>");
		for(Map.Entry<String, DtActivity> activity : cachedActivities.entrySet()){
			names.add(activity.getKey());
		}
		return names.toArray(new String[0]);
	}

}
