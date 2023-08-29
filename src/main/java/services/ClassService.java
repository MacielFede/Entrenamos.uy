package services;

import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dataTypes.DtClass;
import entities.Class;
import repository.GenericRepository;

public class ClassService {
	@PersistenceContext
    private EntityManager entityManager;
	private final GenericRepository<Class> classRepository;
	
	public ClassService(EntityManager entityManager){
		this.entityManager = entityManager;
		this.classRepository = new GenericRepository<Class>(entityManager, Class.class);
	}
	
	public DtClass getClassByName(String name) {
		DtClass dtc = null;
		return dtc;
	}
	
	public Map<String, DtClass> getAllClasses() {
		Map<String, DtClass> classes = new TreeMap<String, DtClass>();
		String [] joinProperties = {"enrollments.user"};
		for(Class c : classRepository.findAll(joinProperties)){
			classes.put(c.getName(), c.getData());
		}
		entityManager.close();
		return classes;
	}
	
}
