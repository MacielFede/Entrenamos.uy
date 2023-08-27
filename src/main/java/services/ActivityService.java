package services;

import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dataTypes.DtActivity;
import dataTypes.DtClass;
import entities.Activity;
import entities.Member;
import entities.Professor;
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
		return classes;
	}
	
	public boolean checkActivityAvialability(String name) {
		return activityRepository.findById(name, "name") == null ? true : false;
	}
	
	public Map<String, DtActivity> getAllActivity() {
		Map<String, DtActivity> activities = new TreeMap<String, DtActivity>();
		for(Activity a : activityRepository.findAll()){
			activities.put(a.getName(), a.getData());
		}
		return activities;
	}

    public void updateActivity(DtActivity dtA) {
		entityManager.getTransaction().begin();
		Activity updatedActivity = new Activity();
		updatedActivity.setName(dtA.getName());
		updatedActivity.setDescription(dtA.getDescription());
		updatedActivity.setDuration(dtA.getDuration());
		updatedActivity.setPrice(dtA.getPrice());
		updatedActivity.setregistryDate(dtA.getRegistryDate());
		activityRepository.update(updatedActivity);
		entityManager.getTransaction().commit();
    }
}