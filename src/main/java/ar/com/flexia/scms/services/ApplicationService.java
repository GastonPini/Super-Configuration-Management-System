package ar.com.flexia.scms.services;

import java.util.List;
import java.util.Optional;

import ar.com.flexia.scms.api.dto.NewApplication;
import ar.com.flexia.scms.model.entity.*;

public interface ApplicationService {

	public Application createApplication(NewApplication newApplication);
	
	public Optional<Application> findApp(String name);

	public Application findById(long applicationId);
	
	public List<Application> findAll();	

}