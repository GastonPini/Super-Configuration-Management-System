package ar.com.flexia.scms.services;

import java.util.List;

import ar.com.flexia.scms.api.dto.NewApplication;
import ar.com.flexia.scms.api.dto.NewEnvironment;
import ar.com.flexia.scms.model.entity.*;


public interface EnvironmentService {

	public Environment createEnvironment(NewEnvironment newEnvironment, NewApplication newApplication);
	
	public Environment findById(long environmentId);
	
	public List<Environment> findAll();

	public Environment addConfiguration(long environmentId, Configuration newConfiguration);

	public Environment removeConfiguration(long environmentId, long configurationId);

	public List<Configuration> getConfigurations(long applicationId, long environmentId, String apiKey, String apiSecret);

	

	

	

}