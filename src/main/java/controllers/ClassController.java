package controllers;

import java.util.Map;

import dataTypes.DtClass;
import dataTypes.DtUser;
import interfaces.ClassInterface;

public class ClassController implements ClassInterface {
	public ClassController() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean registerUserToClass(DtClass rclass, DtUser user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DtClass showClassInfo(String className) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, DtClass> listClassesBySportActivity(Integer idSportActivity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DtClass chooseClassByName(String className) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, DtClass> listClassesDictationRanking() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createSportClass(DtClass newClass, Integer idSportActivity) {
		// TODO Auto-generated method stub
		return false;
	}

}
