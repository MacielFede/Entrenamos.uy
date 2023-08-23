package controllers;

import java.util.Map;
import java.util.TreeMap;

import dataTypes.DtClass;
import dataTypes.DtUser;
import interfaces.ClassInterface;
import services.ActivityService;
import services.ServiceFactory;

public class ClassController implements ClassInterface {
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	
	public ClassController() {
		super();
		// TODO Auto-generated constructor stub
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
	public Map<String, DtClass> listClassesBySportActivity(String nameActivity) {
		ActivityService activityService = serviceFactory.getActivityService();
		Map<String, DtClass> activities = activityService.getClassesByActivity(nameActivity);
		return activities;
	}

	@Override
	public DtClass chooseClassByName(String className) {
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

}
