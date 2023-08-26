package dataTypes;

import java.util.Map;

public class DtInstitute {

	private final String name;

	private final String description;

	private final String url;

	private final Map<String, DtActivity> activities;
	
	public DtInstitute(String name, String description, String url, Map<String, DtActivity> activities) {
		super();
		this.name = name;
		this.description = description;
		this.url = url;
		this.activities = activities;
	}
	
	public Map<String, DtActivity> getActivities() {
		return activities;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}


	public String getUrl() {
		return url;
	}

}
