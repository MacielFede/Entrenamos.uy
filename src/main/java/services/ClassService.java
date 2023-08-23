package services;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dataTypes.DtClass;

public class ClassService {
	@PersistenceContext
    private EntityManager entityManager;
	
	public ClassService(EntityManager entityManagers){
		this.entityManager = entityManager;
	}
	
	public DtClass getClassByName(String name) {
		DtClass dtc = null;
		return dtc;
	}
	
	public Map<String, DtClass> getAllClasses() {
		Map<String, DtClass> l = null;
		return l;
	}
}
