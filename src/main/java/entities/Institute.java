package entities;

import java.util.Map;
import java.util.TreeMap;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import dataTypes.DtClass;
import dataTypes.DtEnrollment;
import dataTypes.DtInstitute;

@Entity
public class Institute {

	@Id
	private String name;

	private String description;

	private String url;

// Add these to the constructor later
//	@OneToMany(mappedBy = "institute", cascade = CascadeType.ALL)
//	private Map<string, Activity> activities;
//
//	@OneToMany(mappedBy = "institute", cascade = CascadeType.ALL)
//	private Map<String, Professor> professors;

	public String getName() {
		return name;
	}

	public Institute() {
		super();
	}

	public Institute(String name, String description, String url) {
		super();
		this.name = name;
		this.description = description;
		this.url = url;
//		this.professors = professors;
//		this.activities = activities;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public DtInstitute getData(){
		// Generate all the logic after having all the entities.
		return new DtInstitute(this.name, this.description, this.url);
    }
	
//	public Activity getActivity(String activityName) {
//		// Logic here
//	}
	
//	public Map<string, DtActivity> getDataActivities() {
//		// Logic here
//	}
	
//	public Map<String, Class>getClassesByActivity(String activityName) {
//		// Logic here
//	}

}
