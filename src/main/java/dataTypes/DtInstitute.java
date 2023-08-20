package dataTypes;

public class DtInstitute {

	private String name;

	private String description;

	private String url;

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
	}

	public String getUrl() {
		return url;
	}

}
