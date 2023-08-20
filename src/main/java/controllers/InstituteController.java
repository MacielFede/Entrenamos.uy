package controllers;

import java.util.Map;

import dataTypes.DtActivity;
import dataTypes.DtClass;
import dataTypes.DtSportActivity;
import dataTypes.DtSportInstitute;
import interfaces.InstituteInterface;

public class InstituteController implements InstituteInterface {
	public InstituteController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Map<String, DtSportInstitute> listSportInstitutes() {
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
	public Map<String, DtSportActivity> listSportsActivitiesRanking() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addNewSportActivity(DtSportActivity sportActivity, Integer idInstitute) {
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
	public DtSportActivity getActivity(String activityName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DtSportInstitute chooseSportInstitute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
