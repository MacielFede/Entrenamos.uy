package dataTypes;

public class DtInstitute {

	private String name;

	private String description;

	private String url;

// Add these to the constructor later
//		private Map<string, DtActivity> activities;
//		private Map<String, DtProfessor> professors;

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public DtInstitute(String name, String description, String url) {
		super();
		this.name = name;
		this.description = description;
		this.url = url;
//		this.professors = professors;
//		this.activities = activities;
	}

	public String getUrl() {
		return url;
	}

//	public Map<string,DtActivity> getActivities() {
//		return this.activities;
//	}

//public Map<string, DtProfessor> getProfessors() {
//	return this.professors; 
//}

}
