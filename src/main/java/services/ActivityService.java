package services;

import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dataTypes.DtActivity;
import dataTypes.DtClass;
import entities.Activity;
import entities.Institute;
import repository.GenericRepository;

public class ActivityService {
	@PersistenceContext
    private EntityManager entityManager;
	private final GenericRepository<Activity> activityRepository;
	
	public ActivityService(EntityManager entityManager){
		this.entityManager = entityManager;
		this.activityRepository = new GenericRepository<Activity>(entityManager ,Activity.class);
	}
	
	public DtActivity getActivityByName(String name) {
		DtActivity dti = null;
		return dti;
	}
	
	public Map<String, DtClass> getClassesByActivity(String nameActivity){
		Map<String, DtClass> classes = new TreeMap<String, DtClass>();
		for(entities.Class a : activityRepository.findById(nameActivity, "name").getClasses().values()){
			classes.put(a.getName(), a.getData());
		}
		entityManager.close();
		return classes;
	}
	
	public Map<String, DtActivity> getAllActivity() {
		Map<String, DtActivity> activities = new TreeMap<String, DtActivity>();
		for(Activity a : activityRepository.findAll()){
			activities.put(a.getName(), a.getData());
		}
		entityManager.close();
		return activities;
	}
}