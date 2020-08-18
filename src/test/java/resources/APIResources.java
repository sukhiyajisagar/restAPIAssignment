package resources;

//enum is special class in java which has collection of constants or  methods
public enum APIResources {

	AddEmployeeAPI("/api/v1/create"), getEmployeeAPI("api/v1/employee/{id}"), updateEmployeeAPI("api/v1/update/{id}");

	private String resource;

	APIResources(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}

}
