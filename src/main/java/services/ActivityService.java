package services;

import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dataTypes.DtActivity;
import entities.Activity;
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
	
	public Map<String, DtActivity> getAllActivity() {
		Map<String, DtActivity> activities = new TreeMap<String, DtActivity>();
		for(Activity a : activityRepository.findAll()){
			activities.put(a.getName(), a.getData());
		}
		return activities;
	}
}
