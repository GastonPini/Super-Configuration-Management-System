package ar.com.flexia.scms.api.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexia.scms.api.dto.*;
import ar.com.flexia.scms.model.entity.*;
import ar.com.flexia.scms.model.repository.*;
import ar.com.flexia.scms.services.*;


@RestController
@RequestMapping(path = "/environment")
public class EnvironmentController {

	private EnvironmentService service;
	private EnvironmentRepository repo;
	
	public EnvironmentController(EnvironmentService service, EnvironmentRepository repo,
			ConfigurationRepository configRepo, ConfigurationService configService) {
		super();
		this.service = service;
		this.repo = repo;
		this.configRepo = configRepo;
		this.configService = configService;
	}

	@Secured({ "ADMIN" })
	@PostMapping
	public Environment createEnvironment(@RequestBody NewEnvironment request) {
		
		NewApplication a = new NewApplication(request.getApplication()); 
		return service.createEnvironment(request, a);		
	}
	
	@Secured({ "ADMIN" })
	@GetMapping(path = "/{environmentId}")
	public Environment getEnvironment(@PathVariable (value = "environmentId") Long environmentId) {
		return service.findById(environmentId);
	}

	@Secured({"ADMIN"})
	@GetMapping
	public List<Environment> getEnvironments() {
		return service.findAll();
	}
	
	///////////////////// SECCIÓN CONFIGURATION /////////////////////
	
	private ConfigurationRepository configRepo;
	
	private ConfigurationService configService;
	
	@Secured({"ADMIN"})
	@PostMapping(path = "/{environmentId}/configurations")
	@Transactional
	public void addConfiguration(@PathVariable("environmentId") long environmentId, @RequestBody List<Configuration> request) {
		
		Environment env = repo.findById(environmentId).orElseThrow(NoSuchElementException::new);
		
		List<Long> ids = request.stream().map(Configuration::getId).collect(Collectors.toList());
		
		configRepo.findAllById(ids).forEach(c -> env.addConfiguration(c));
		
		repo.save(env);
	}

	// EDICIÓN DE CONFIGURATION DESDE DIRECCIÓN /environment
	@Secured({"ADMIN"})
	@PutMapping(path = "/{environmentId}/configurations/{configId}")
	public void editConfiguration(@PathVariable (value = "configId") Long configId, @RequestBody NewConfiguration newValue) {
		configService.updateConfiguration(configId, newValue);
	}
	
	// ELIMINACIÓN DE CONFIGURATION DESDE DIRECCIÓN /environment
	@Secured({"ADMIN"})
	@DeleteMapping(path = "/{environmentId}/configurations/{configId}")
	public void deleteConfiguration(@PathVariable (value = "configId") Long configId) {
		configService.deleteConfiguration(configId);
	}
	
}