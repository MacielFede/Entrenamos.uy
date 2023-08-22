package controllers;

import java.util.List;
import java.util.Map;

import dataTypes.DtActivity;
import dataTypes.DtClass;
import dataTypes.DtInstitute;
import interfaces.InstituteInterface;

public class InstituteController implements InstituteInterface {
	public InstituteController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Map<String, DtInstitute> listSportInstitutes() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Map<String, DtActivity> selectInstitution(String institutionName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, DtClass> chooseActivity(DtActivity activity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DtActivity> listSportsActivitiesRanking() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addNewSportActivity(DtActivity sportActivity, Integer idInstitute) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modiFySportInstitute(String desc, String url) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registerInstitution(String name, String description, String url) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DtActivity getActivity(String activityName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DtInstitute chooseSportInstitute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
