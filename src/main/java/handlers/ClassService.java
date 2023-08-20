package handlers;

import java.util.Map;

import dataTypes.DtClass;

public class ClassService {
	private static ClassService instance = null;
	
	private ClassService(){}
	
	public static ClassService getInstance() {
		if (instance == null)
			instance = new ClassService();
		return instance;
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
