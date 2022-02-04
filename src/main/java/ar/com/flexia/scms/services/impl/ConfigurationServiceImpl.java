package ar.com.flexia.scms.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ar.com.flexia.scms.api.dto.NewApplication;
import ar.com.flexia.scms.api.dto.NewConfiguration;
import ar.com.flexia.scms.api.dto.NewEnvironment;
import ar.com.flexia.scms.model.entity.*;
import ar.com.flexia.scms.model.repository.*;
import ar.com.flexia.scms.services.*;


@Service
public class ConfigurationServiceImpl implements ConfigurationService {

	private ConfigurationRepository repo;
	
	private EnvironmentService envService;
	
	private EnvironmentRepository envRepo;
	
	private final ApplicationRepository appRepo;
	
	public ConfigurationServiceImpl(ConfigurationRepository configRepo, EnvironmentService envService, EnvironmentRepository envRepo, ApplicationRepository appRepo) {
		super();
		this.repo = configRepo;
		this.envService = envService;
		this.envRepo = envRepo;
		this.appRepo = appRepo;
	}
	
	@Override
	public Configuration createConfiguration(NewConfiguration newConfiguration, NewEnvironment newEnvironment, NewApplication newApplication) {
		
		Optional<Environment> env = envRepo.findByNameAndApplication(newEnvironment.getName(), appRepo.findByName(newApplication.getName()));
		
		if(env.isPresent())
		
			return repo.save(new Configuration(
					newConfiguration.getClave(),
					newConfiguration.getValor(),
					env.get()
					)
					);
		
		else
		
			return repo.save(new Configuration(
					newConfiguration.getClave(),
					newConfiguration.getValor(),
					envService.createEnvironment(newEnvironment, newApplication)
					)
					);
	}
	
	@Override
	public Configuration findById(Long configurationId) {
		return repo.findById(configurationId)
				.orElseThrow(() -> new APIException("configuration not found"));
	}
	
	public List<Configuration> findAll() {
		return repo.findAll();
	}
	
	@Override
	public void deleteConfiguration(long configId) {
		repo.deleteById(configId);		
	}
		
	@Override
	public Configuration updateConfiguration(long configId, NewConfiguration newValue) {
		Configuration c = repo.findById(configId).orElseThrow(NoSuchElementException::new);
		if (null != newValue.getClave()) {
			c.setClave(newValue.getClave());
		}
		
		if (null != newValue.getValor()) {
			c.setValor(newValue.getValor());
		}

		return repo.save(c);		
	}

}