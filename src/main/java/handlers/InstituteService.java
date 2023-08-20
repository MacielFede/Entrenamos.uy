package handlers;

import java.util.Map;

import dataTypes.DtInstitute;

public class InstituteService {
	private static InstituteService instance = null;
	
	private InstituteService(){}
	
	public static InstituteService getInstance() {
		if (instance == null)
			instance = new InstituteService();
		return instance;
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
