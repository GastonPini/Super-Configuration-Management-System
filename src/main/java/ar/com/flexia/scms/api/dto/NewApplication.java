package ar.com.flexia.scms.api.dto;

import ar.com.flexia.scms.model.entity.Application;

public class NewApplication {

	private String name;
	
	private String description;
	
	public NewApplication() {
		super();
	}
	
	public NewApplication(Application application) {
		this.name = application.getName();
		this.description = application.getDescription();
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
}