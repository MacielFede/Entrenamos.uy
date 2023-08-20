package handlers;

import java.util.Map;

import dataTypes.DtSportInstitute;

public class InstituteService {
	private static InstituteService instance = null;
	
	private InstituteService(){}
	
	public static InstituteService getInstance() {
		if (instance == null)
			instance = new InstituteService();
		return instance;
	}
	
	public DtSportInstitute getInstituteByName(String name) {
		DtSportInstitute DtU = null;
		return DtU;
	}
	
	public Map<String, DtSportInstitute> getAllInstitutes() {
		Map<String, DtSportInstitute> lDtU = null;
		return lDtU;
	}
}
