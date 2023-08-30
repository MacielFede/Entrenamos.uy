package entities;

import java.util.Map;
import java.util.TreeMap;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import dataTypes.DtActivity;
import dataTypes.DtInstitute;
import dataTypes.DtProfessor;

@Entity
public class Institute {

	@Id
	private String name;

	private String description;

	private String url;

	@OneToMany(cascade = CascadeType.ALL)
	private Map<String, Activity> activities;

	@OneToMany(mappedBy = "sportInstitution",cascade = CascadeType.ALL)
	private Map<String, Professor> professors;

	public String getName() {
		return name;
	}

	public Institute() {
		super();
	}

	public Institute(String name, String description, String url, Map<String, Professor> professors,
			Map<String, Activity> activities) {
		super();
		this.name = name;
		this.description = description;
		this.url = url;
		this.professors = professors;
		this.activities = activities;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Activity> getActivities() {
		return activities;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public DtInstitute getData() {
		Map<String, DtActivity> activitiesList = new TreeMap<String, DtActivity>();
		for(Activity a : this.activities.values()) {
			activitiesList.put(a.getName(), a.getData());
		}
		
		// New, getting the institute professors
		Map<String, DtProfessor> professorList = new TreeMap<String, DtProfessor>();
		for(Professor p : this.professors.values()) {
			professorList.put(p.getName(),(DtProfessor) p.getData());
		}
		
		
		return new DtInstitute(this.name, this.description, this.url, activitiesList, professorList);
	}

	public Activity getActivity(String activityName) {
		// Logic here
		return null;
	}

	public Map<String, DtActivity> getDataActivities() {
		Map<String, DtActivity> activityList = new TreeMap<String, DtActivity>();
		for(Activity a : activities.values()) {
			activityList.put(a.getName(), a.getData());
		}
		return activityList;
	}

	public Map<String, Class> getClassesByActivity(String activityName) {
		// Logic here
		return null;
	}

}
