package ar.com.flexia.scms.api.dto;

import ar.com.flexia.scms.model.entity.Application;
import ar.com.flexia.scms.model.entity.Environment;

public class NewEnvironment {

	private String name;
	
	private String description;
	
	private Application application;
	
	public NewEnvironment() {
		super();
	}

	public NewEnvironment(Environment environment) {
		this.name = environment.getName();
		this.description = environment.getDescription();
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public Application getApplication() {
		return application;
	}
	
}