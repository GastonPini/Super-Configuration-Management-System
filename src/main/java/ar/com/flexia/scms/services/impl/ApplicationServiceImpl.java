package ar.com.flexia.scms.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import ar.com.flexia.scms.api.dto.*;
import ar.com.flexia.scms.model.entity.*;
import ar.com.flexia.scms.model.repository.*;
import ar.com.flexia.scms.services.*;


@Service
public class ApplicationServiceImpl implements ApplicationService {
	
	private final ApplicationRepository repo;
	
	public ApplicationServiceImpl(ApplicationRepository repo) {
		super();
		this.repo = repo;
	}
	
	@Override
	public Application createApplication(NewApplication newApplication) {
		
		Optional<Application> application = repo.findByName(newApplication.getName());
		if(application.isPresent())
			throw new IllegalArgumentException("La aplicación " + newApplication.getName() + " ya existe, ingrese otro nombre.");
		
		Application app = new Application(newApplication.getName(), newApplication.getDescription());
		return repo.save(app);
	}
	
	@Override
	public Optional<Application> findApp(String name) {
		return repo.findByName(name);
	}
	
	@Override
	public Application findById(long applicationId) {
		return repo.findById(applicationId).orElseThrow(() -> new APIException("App no encontrada"));
	}
	
	@Override
	public List<Application> findAll() {
		return repo.findAll();
	}
	
}