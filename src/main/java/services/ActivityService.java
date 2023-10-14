package services;

import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dataTypes.DtActivity;
import dataTypes.DtClass;
import entities.Activity;
import entities.Class;
import repository.GenericRepository;

public class ActivityService {
	@PersistenceContext
	private EntityManager entityManager;
	private final GenericRepository<Activity> activityRepository;

	public ActivityService(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.activityRepository = new GenericRepository<Activity>(entityManager, Activity.class);
	}

	public void addClassToActivity(DtClass newClass, String activityName) {
		try {
			Class createdClass = new Class(newClass.getName(),newClass.getDateAndTime(), newClass.getRegisterDate(), newClass.getUrl(), "");
			entityManager.getTransaction().begin();
			Activity activity = activityRepository.findById(activityName, "name", new String[]{"classes.enrollments.user"});
			activity.getClasses().put(createdClass.getName(), createdClass);
			activityRepository.save(activity);
			entityManager.getTransaction().commit();
			entityManager.close();
		} catch (Exception e) {
			System.out.println(e);
			entityManager.getTransaction().rollback();
			entityManager.close();
		}
	}

	public Map<String, DtClass> getClassesByActivity(String nameActivity) {
		Map<String, DtClass> classes = new TreeMap<String, DtClass>();
		for (entities.Class a : activityRepository.findById(nameActivity, "name", new String[]{"classes.enrollments.user"}).getClasses().values()) {
			classes.put(a.getName(), a.getData());
		}
		entityManager.close();
		return classes;
	}

	public boolean checkActivityAvialability(String name) {
		try {
			if (activityRepository.findById(name, "name") == null) {
				entityManager.close();
				return true;
			}
			entityManager.close();
			return false;
		} catch (Exception e) {
			entityManager.close();
			return true;
		}
	}

	public Map<String, DtActivity> getAllActivity() {
		Map<String, DtActivity> activities = new TreeMap<String, DtActivity>();
		String[] joinProperties = { "classes.enrollments.user" };
		for (Activity a : activityRepository.findAll(joinProperties)) {
			activities.put(a.getName(), a.getData());
		}
		entityManager.close();
		return activities;
	}

	public void updateActivity(DtActivity dtA) {
		try {
			entityManager.getTransaction().begin();
			Activity updatedActivity = activityRepository.findById(dtA.getName(), "name", new String[]{"classes"});
			updatedActivity.setDescription(dtA.getDescription());
			updatedActivity.setDuration(dtA.getDuration());
			updatedActivity.setPrice(dtA.getPrice());
			updatedActivity.setregistryDate(dtA.getRegistryDate());
			activityRepository.update(updatedActivity);
			entityManager.getTransaction().commit();
			entityManager.close();
		} catch (Exception e) {
			entityManager.close();
		}
	}
}