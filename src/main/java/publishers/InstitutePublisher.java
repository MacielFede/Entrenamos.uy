package publishers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import dataTypes.DtActivity;
import dataTypes.DtClass;
import dataTypes.DtEnrollment;
import dataTypes.DtEnrollment;
import dataTypes.DtInstitute;
import exceptions.EmptyRequiredFieldException;
import exceptions.NullPriceException;
import interfaces.ControllerFactory;
import interfaces.InstituteInterface;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class InstitutePublisher {
	private ControllerFactory factory;
	private InstituteInterface icon;
	private Endpoint endpoint;
	private String baseUrl;
	
	public InstitutePublisher() {
		factory = ControllerFactory.getInstance();
		icon = factory.getInstituteInterface();
		try {
			InputStream configStream = InstitutePublisher.class.getClassLoader().getResourceAsStream("config.properties");
			if (configStream != null) {
				Properties properties = new Properties();
				properties.load(configStream);
				baseUrl = properties.getProperty("baseUrl");
			} 
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}

	@WebMethod(exclude = true)
	public void publish() {
		endpoint = Endpoint.publish(baseUrl + "/instituteController", this);
		System.out.println(baseUrl + "/instituteController");
	}
	
	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
        return endpoint;
	}
	
	@WebMethod(exclude = true)
	public void stopPublishing() {
	    if (endpoint != null) {
	        endpoint.stop();
	        System.out.println("Webservice en " + baseUrl + "/instituteController" + " ha sido detenido.");
	    } else {
	        System.out.println("Webservice no estaba activo.");
	    }
	}

	
	@WebMethod
	public boolean checkClassNameAvailability(String className, DtEnrollment testEnrollment) {
		return icon.checkClassNameAvailability(className);
	}
	
	@WebMethod
	public boolean checkActivityAvialability(String name) {
		return icon.checkActivityAvialability(name);
	}
	
	@WebMethod
	public DtClass chooseClassByName(String className) {
		return icon.chooseClassByName(className);
	}
	
	@WebMethod
	public DtClass [] listClassesDictationRanking() {
		List<DtClass> lstClassDictationRanking = icon.listClassesDictationRanking();
		DtClass[] ret = new DtClass[lstClassDictationRanking.size()];
		int i = 0;
  		for (DtClass dt : lstClassDictationRanking) {
  			ret[i] = dt;
  			i++;
  		}
		return ret;
	}
	
	@WebMethod
	public void createSportClass(DtClass newClass, String idSportActivity, String idProfessor) {
		icon.createSportClass(newClass, idSportActivity, idProfessor);
	}
	
	@WebMethod
	public DtInstitute [] listSportInstitutes() {
		Map<String, DtInstitute> institutes = icon.listSportInstitutes();
		DtInstitute[] instituteArray = new DtInstitute[institutes.size()];
		int i = 0;
		for (DtInstitute institute : institutes.values()) {
		    instituteArray[i] = institute;
		    i++;
		}
		return instituteArray;
	}
	
	@WebMethod
	public DtActivity [] selectInstitution(String institutionName) {
		Map<String,DtActivity> activities = icon.selectInstitution(institutionName);
		DtActivity[] activitiesArray = new DtActivity[activities.size()];
		int i = 0;
		for (DtActivity activity : activities.values()) {
		    activitiesArray[i] = activity;
		    i++;
		}
		return activitiesArray;
	}
	
	@WebMethod
	public DtClass[] chooseActivity(String activity) {
		Map<String,DtClass> classes = icon.chooseActivity(activity);
		DtClass[] classesArray = new DtClass[classes.size()];
		int i = 0;
		for (DtClass aClass: classes.values()) {
			classesArray[i] = aClass;
			i++;
		}
		return classesArray;
	}
	
	@WebMethod
	public DtActivity [] listSportsActivitiesRanking() {
		List<DtActivity> lstSportActivitiesRanking = icon.listSportsActivitiesRanking();
		DtActivity[] ret = new DtActivity[lstSportActivitiesRanking.size()];
		int i = 0;
  		for (DtActivity dt : lstSportActivitiesRanking) {
  			ret[i] = dt;
  			i++;
  		}
		return ret;
	}
	
	@WebMethod
	public void modiFySportInstitute(DtInstitute institute) {
		icon.modiFySportInstitute(institute);
	}
	
	@WebMethod
	public void addNewSportActivity(DtActivity sportActivity, String nameInstitute) {
		icon.addNewSportActivity(sportActivity, nameInstitute);
	}
	
	@WebMethod
	public void registerInstitution(String name, String description, String url) {
		icon.registerInstitution(name, description, url);
	}
	
	@WebMethod
	public DtActivity getActivity(String activityName) {
		return icon.getActivity(activityName);
	}
	
	@WebMethod
	void updateActivityInfo(DtActivity dtA) throws EmptyRequiredFieldException, NullPriceException {
		icon.updateActivityInfo(dtA);
	}
	
	@WebMethod
	public DtInstitute chooseSportInstitute(String name) {
		return icon.chooseSportInstitute(name);
	}
	
	@WebMethod
	public boolean checkInstitutionAvialability(String name) {
		return icon.checkInstitutionAvialability(name);
	}
	
	@WebMethod
    String[] listSportActivitiesByName() {
		return icon.listSportActivitiesByName();
	}
	
	@WebMethod
    public DtActivity[] getAllActivities() {
		Map<String,DtActivity> activities = icon.getAllActivities();
		DtActivity[] ret = new DtActivity[activities.size()];
		int i = 0;
  		for (DtActivity dt : activities.values()) {
  			ret[i] = dt;
  			i++;
  		}
		return ret;
	}
	
	@WebMethod
	DtActivity chooseActivityByName(String activity) {
		return icon.chooseActivityByName(activity);
	}
	
}
