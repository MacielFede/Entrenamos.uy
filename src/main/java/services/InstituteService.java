package services;

import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dataTypes.DtActivity;
import dataTypes.DtInstitute;
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

	public Map<String, DtActivity> getActivitiesByInstitute(String instituteName){
		Map<String, DtActivity> activities = new TreeMap<String, DtActivity>();
		activities = instituteRepository.findById(instituteName, "name" , new String[] {"activities"}).getDataActivities();
		entityManager.close();
		return activities;
	}
	
	public Map<String, DtInstitute> getAllInstitutes() {
		Map<String, DtInstitute> institutes = new TreeMap<String, DtInstitute>();
		for(Institute i : instituteRepository.findAll()) {
			institutes.put(i.getName(), i.getData());
		}
		entityManager.close();
		return institutes;
	}
}
