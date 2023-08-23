package controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import dataTypes.DtActivity;
import dataTypes.DtClass;
import dataTypes.DtInstitute;
import interfaces.InstituteInterface;
import services.ActivityService;
import services.InstituteService;
import services.ServiceFactory;

public class InstituteController implements InstituteInterface {
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	
	public InstituteController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Map<String, DtInstitute> listSportInstitutes() {
		InstituteService instituteService = serviceFactory.getInstituteService();
		Map<String, DtInstitute> institutes = instituteService.getAllInstitutes();
		return institutes;
	}

	@Override
	public Map<String, DtActivity> selectInstitution(String institutionName){
		InstituteService instituteService = serviceFactory.getInstituteService();
		Map<String, DtActivity> activities = instituteService.getActivitiesByInstitute(institutionName);
		return activities;
	}

	@Override
	public Map<String, DtClass> chooseActivity(DtActivity activity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DtActivity> listSportsActivitiesRanking() {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DtInstitute chooseSportInstitute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
