package ar.com.flexia.scms.services;


import java.util.List;

import ar.com.flexia.scms.api.dto.NewApplication;
import ar.com.flexia.scms.api.dto.NewConfiguration;
import ar.com.flexia.scms.api.dto.NewEnvironment;
import ar.com.flexia.scms.model.entity.*;


public interface ConfigurationService {

//	public Configuration createConfiguration(NewConfiguration newConfiguration);

	public Configuration createConfiguration(NewConfiguration newConfiguration, NewEnvironment newEnvironment,
			NewApplication newApplication);
	
	public Configuration findById(Long configurationId);
	
	public List<Configuration> findAll();
	
	public void deleteConfiguration(long configId);

	public Configuration updateConfiguration(long configId, NewConfiguration newValue);

	
	
}