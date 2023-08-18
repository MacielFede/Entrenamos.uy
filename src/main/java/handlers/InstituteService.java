package handlers;

import java.util.List;

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
	
	public List<DtSportInstitute> getAllInstitutes() {
		List<DtSportInstitute> lDtU = null;
		return lDtU;
	}
}
