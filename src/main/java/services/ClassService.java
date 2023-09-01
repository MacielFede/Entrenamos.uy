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

	public ClassService(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.classRepository = new GenericRepository<Class>(entityManager, Class.class);
	}

	public Class getClassByName(String name) {
		try {
			return this.classRepository.findById(name, "name");
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public boolean checkClassAvailability(String className) {
		try {
			return classRepository.findById(className, "name") == null ? true : false;
		} catch (Exception e) {
			return true;
		}
	}

	public Map<String, DtClass> getAllClasses() {
		Map<String, DtClass> classes = new TreeMap<String, DtClass>();
		String[] joinProperties = { "enrollments.user" };
		for (Class c : classRepository.findAll(joinProperties)) {
			classes.put(c.getName(), c.getData());
		}
		entityManager.close();
		return classes;
	}

}
