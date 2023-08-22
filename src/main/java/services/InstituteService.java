package services;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dataTypes.DtInstitute;

public class InstituteService {
	@PersistenceContext
    private EntityManager entityManager;
	
	public InstituteService(EntityManager entityManagers){
		this.entityManager = entityManager;
	}
	
	public DtInstitute getInstituteByName(String name) {
		DtInstitute DtU = null;
		return DtU;
	}
	
	public Map<String, DtInstitute> getAllInstitutes() {
		Map<String, DtInstitute> lDtU = null;
		return lDtU;
	}
}
