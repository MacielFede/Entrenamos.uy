package services;

import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dataTypes.DtActivity;
import dataTypes.DtInstitute;
import entities.Activity;
import entities.Class;
import entities.Institute;
import repository.GenericRepository;

public class InstituteService {
	@PersistenceContext
    private EntityManager entityManager;
	private final GenericRepository<Institute> instituteRepository;
	
	public InstituteService(EntityManager entityManager){
		this.entityManager = entityManager;
		this.instituteRepository = new GenericRepository<Institute>(entityManager ,Institute.class);
	}
	
	public DtInstitute getInstituteByName(String name) {
		DtInstitute DtU = null;
		return DtU;
	}
	
	public void addActivityAtInstitute(String name, DtActivity activity){
		entityManager.getTransaction().begin();
		Institute institute = instituteRepository.findById(name, "name");
		Activity newActivity = 
				new Activity(
						activity.getName(),
						activity.getDescription(), 
						activity.getDuration(), 
						activity.getRegistryDate(),
						activity.getPrice(), 
						new TreeMap<String, Class>());
		institute.getActivities().put(activity.getName(), newActivity);
		instituteRepository.save(institute);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public Map<String, DtActivity> getActivitiesByInstitute(String instituteName){
		Map<String, DtActivity> activities = new TreeMap<String, DtActivity>();
		//String[] joinProperties = new String[]{"activities.classes"};
		activities = instituteRepository.findById(instituteName, "name").getDataActivities();
		entityManager.close();
		return activities;
	}
	
	public Map<String, DtInstitute> getAllInstitutes() {
		Map<String, DtInstitute> institutes = new TreeMap<String, DtInstitute>();
		//String[] joinProperties = new String[]{"activities.classes"};
		for(Institute i : instituteRepository.findAll()) {
			institutes.put(i.getName(), i.getData());
		}
		entityManager.close();
		return institutes;
	}
}
