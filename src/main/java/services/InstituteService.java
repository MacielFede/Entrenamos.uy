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
import entities.Professor;
import repository.GenericRepository;

public class InstituteService {
	@PersistenceContext
    private EntityManager entityManager;
	private final GenericRepository<Institute> instituteRepository;
	
	public InstituteService(EntityManager entityManager){
		this.entityManager = entityManager;
		this.instituteRepository = new GenericRepository<Institute>(entityManager ,Institute.class);
	}
	
	public Institute getInstituteByName(String name) {
		try {
			return instituteRepository.findById(name, "name", new String[]{"activities", "professors"});
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public boolean checkInstitutionAvialability(String name) {
		try {
			if(instituteRepository.findById(name, "name") == null){
				entityManager.close();
				return true;
			}
			entityManager.close();
			return false;
		}
		catch(Exception e) {
			return true;
		}
	}
	
	
	public void addActivityAtInstitute(String name, DtActivity activity){
		entityManager.getTransaction().begin();
		Institute institute = instituteRepository.findById(name, "name", new String[]{"activities.classes", "professors"});
		Activity newActivity = 
				new Activity(
						activity.getName(),
						activity.getDescription(), 
						activity.getImgName(),
						activity.getDuration(), 
						activity.getRegistryDate(),
						activity.getPrice(), 
						new TreeMap<String, Class>());
		institute.getActivities().put(activity.getName(), newActivity);
		instituteRepository.save(institute);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public void createInstitute(String name, String description, String url) {
		entityManager.getTransaction().begin();
		Institute institute = new Institute(name,description,url, new TreeMap<String, Professor>(),new TreeMap<String, Activity>());
		instituteRepository.save(institute);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public Map<String, DtActivity> getActivitiesByInstitute(String instituteName){
		Map<String, DtActivity> activities = new TreeMap<String, DtActivity>();
		String[] joinProperties = new String[]{"activities.classes.enrollments"};
		activities = instituteRepository.findById(instituteName, "name", joinProperties).getDataActivities();
		entityManager.close();
		return activities;
	}
	
	public Map<String, DtInstitute> getAllInstitutes() {
		Map<String, DtInstitute> institutes = new TreeMap<String, DtInstitute>();
		String[] joinProperties = new String[]{"activities.classes.enrollments.user", "professors.classes"};
		for(Institute i : instituteRepository.findAll(joinProperties)) {
			institutes.put(i.getName(), i.getData());
		}
		entityManager.close();
		return institutes;
	}
	
    public void updateInstitute(DtInstitute dtI) {
		entityManager.getTransaction().begin();
		Institute updatedInstitute = instituteRepository.findById(dtI.getName(), "name", new String[]{"activities", "professors"});
		//updatedInstitute.setName(dtI.getName());
		updatedInstitute.setDescription(dtI.getDescription());
		updatedInstitute.setUrl(dtI.getUrl());
		instituteRepository.update(updatedInstitute);
		entityManager.getTransaction().commit();
		entityManager.close();
    }

	public Institute addProffesorToInstitute(String institute, Professor newProfessor) {
		try {
			Institute instituteChanged = instituteRepository.findById(institute, "name", new String[]{"professors"});
			instituteChanged.getProfessors().put(newProfessor.getNickname(), newProfessor);
			return instituteChanged;
		}
		catch(Exception e) {
			return null;
		}
	}
}
