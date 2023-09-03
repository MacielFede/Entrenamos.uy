package controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import dataTypes.DtActivity;
import dataTypes.DtClass;
import dataTypes.DtInstitute;
import dataTypes.DtUser;
import exceptions.EmptyRequiredFieldException;
import exceptions.NullPriceException;
import interfaces.InstituteInterface;
import services.ActivityService;
import services.ClassService;
import services.InstituteService;
import services.ServiceFactory;
import services.UserService;

public class InstituteController implements InstituteInterface {
	private ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private Map<String, DtInstitute> institutesCache = null;
	private Map<String, DtActivity> activitiesCache = null;
	private Map<String, DtClass> classesCache = null;

	public InstituteController() {
		super();
	}

	@Override
	public Map<String, DtInstitute> listSportInstitutes() {
		if (institutesCache == null) {
			listSportInstitutesCache();
		}
		return institutesCache;
	}

	public boolean checkActivityAvialability(String name) {
		ActivityService activityService = serviceFactory.getActivityService();
		return activityService.checkActivityAvialability(name);
	}

	@Override
	public Map<String, DtActivity> selectInstitution(String institutionName) {
		if (institutesCache == null) {
			listSportInstitutesCache();
		}
		activitiesCache = institutesCache.get(institutionName).getActivities();
		return activitiesCache;
	}

	@Override
	public Map<String, DtClass> chooseActivity(String activity) {
		if (activitiesCache == null) {
			listSportInstitutesCache();
		}
		for (DtInstitute i : institutesCache.values()) {
			for (DtActivity a : i.getActivities().values()) {
				if (a.getName().equals(activity)) {
					classesCache = a.getClasses();
					return classesCache;
				}
			}
		}
		return null;
	}

	@Override
	public DtClass chooseClassByName(String className) {
		if (classesCache != null) {
			return classesCache.get(className);
		} else {
			listClassesCache();
			return classesCache.get(className);
		}
	}

	@Override
	public List<DtActivity> listSportsActivitiesRanking() {
		ActivityService activityService = serviceFactory.getActivityService();
		List<DtActivity> activities = new ArrayList<DtActivity>();
		for (DtActivity dt : activityService.getAllActivity().values()) {
			activities.add(dt);
		}
		activities.sort(Comparator.comparingInt(DtActivity::getClassesQuantity).reversed());
		return activities;
	}
	
	@Override
	public Map<String,DtActivity> getAllActivities() {
		ActivityService activityService = serviceFactory.getActivityService();
		return activityService.getAllActivity();
	}

	@Override
	public void addNewSportActivity(DtActivity sportActivity, String nameInstitute) {
		InstituteService instituteService = serviceFactory.getInstituteService();
		instituteService.addActivityAtInstitute(nameInstitute, sportActivity);
	}

	@Override
	public void modiFySportInstitute(DtInstitute institute) {
		serviceFactory.getInstituteService().updateInstitute(institute);;
		//Update the cache
		institutesCache.put(institute.getName(), institute);
	}

	@Override
	public boolean checkInstitutionAvialability(String name) {
		InstituteService instituteService = serviceFactory.getInstituteService();
		return instituteService.checkInstitutionAvialability(name);
	}

	@Override
	public void registerInstitution(String name, String description, String url) {
		InstituteService instituteService = serviceFactory.getInstituteService();
		instituteService.createInstitute(name, description, url);
	}

	@Override
	public DtActivity getActivity(String activityName) {
		if(activitiesCache.isEmpty()){
			activitiesCache = serviceFactory.getActivityService().getAllActivity();
		}
		return activitiesCache.get(activityName);
	}

	@Override
	public void updateActivityInfo(DtActivity dtA) throws EmptyRequiredFieldException, NullPriceException {
		if(dtA.getPrice().isNaN() || dtA.getPrice().isInfinite()){
			throw new NullPriceException();
		}
		if(dtA.getDuration() == 0){
			throw new EmptyRequiredFieldException("Descripción");
		}
		serviceFactory.getActivityService().updateActivity(dtA);
		//Update the cache
		activitiesCache.put(dtA.getName(), dtA);
	}

	@Override
	public DtInstitute chooseSportInstitute(String name) {
		if (institutesCache == null) {
			listSportInstitutesCache();
		}
		
		for (DtInstitute i : institutesCache.values()) {
			if (i.getName().equals(name)) {
				return i;
			}
		}
		return null;
	}
	
	public boolean checkClassNameAvailability(String className) {
		ClassService classService = serviceFactory.getClassService();
		return classService.checkClassAvailability(className);
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
	public List<DtClass> listClassesDictationRanking() {
		ClassService classService = serviceFactory.getClassService();
		List<DtClass> classes = new ArrayList<DtClass>();
		for(DtClass dt : classService.getAllClasses().values()) {
			classes.add(dt);
		}
		classes.sort(Comparator.comparingInt(DtClass::getEnrollmentsQuantity).reversed());
		return classes;
	}

	@Override
	public void createSportClass(DtClass newClass, String idSportActivity, String idProfessor) {
		// Add it to the activity
		ActivityService activityService = serviceFactory.getActivityService();
		activityService.addClassToActivity(newClass, idSportActivity);
		// Add it to the professor
		UserService userService = serviceFactory.getUserService();
		userService.addClassToProfessor(newClass, idProfessor);	
	}

	private void listSportInstitutesCache() {
		InstituteService instituteService = serviceFactory.getInstituteService();
		institutesCache = instituteService.getAllInstitutes();
	}
	
	private void listClassesCache() {
		ClassService classService = serviceFactory.getClassService();
		classesCache = classService.getAllClasses();
	}

	@Override
	public String[] listSportActivitiesByName() {
		// Returns an array with all the activity names and the string "<Nombres>"
		// Also renovates the cached map
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ActivityService activityService = serviceFactory.getActivityService();
		activitiesCache = activityService.getAllActivity();
		List<String> names = new ArrayList<>();
		names.add("<Nombres>");
		for(Map.Entry<String, DtActivity> activity : activitiesCache.entrySet()){
			names.add(activity.getKey());
		}
		return names.toArray(new String[0]);
	}

}
