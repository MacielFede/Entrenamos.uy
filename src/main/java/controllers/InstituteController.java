package controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import dataTypes.DtActivity;
import dataTypes.DtClass;
import dataTypes.DtInstitute;
import dataTypes.DtUser;
import interfaces.InstituteInterface;
import services.ActivityService;
import services.InstituteService;
import services.ServiceFactory;

public class InstituteController implements InstituteInterface {
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private Map<String, DtInstitute> institutesCache 	= null;
	private Map<String, DtActivity> activitiesCache		= null;
	private Map<String, DtClass> classesCache			= null;
	
	public InstituteController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Map<String, DtInstitute> listSportInstitutes() {
		if(institutesCache == null) {
			listSportInstitutesCache();
		}
		return institutesCache;
	}

	@Override
	public Map<String, DtActivity> selectInstitution(String institutionName){
		if(institutesCache == null) {
			listSportInstitutesCache();
		}
		activitiesCache = institutesCache.get(institutionName).getActivities();
		return activitiesCache;
	}

	@Override
	public Map<String, DtClass> chooseActivity(String activity) {
		if(activitiesCache == null) {
			listSportInstitutesCache();
		}
		for(DtInstitute i : institutesCache.values()) {
			for(DtActivity a : i.getActivities().values()) {
				if(a.getName().equals(activity)) {
					classesCache = a.getClasses();
					return classesCache;
				}
			}
		}
		return null;
	}

	@Override
	public DtClass chooseClassByName(String className) {
		if(classesCache !=null) {
			return classesCache.get(className);
		}
		else {
			return null;
		}
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
	
	@Override
	public boolean registerUserToClass(DtClass rclass, DtUser user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DtClass showClassInfo(String className) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, DtClass> listClassesDictationRanking() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createSportClass(DtClass newClass, Integer idSportActivity) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void listSportInstitutesCache() {
		InstituteService instituteService = serviceFactory.getInstituteService();
		institutesCache = instituteService.getAllInstitutes();
	}

}
