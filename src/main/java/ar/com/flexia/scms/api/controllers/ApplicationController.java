package ar.com.flexia.scms.api.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexia.scms.api.dto.*;
import ar.com.flexia.scms.model.entity.*;
import ar.com.flexia.scms.model.repository.*;
import ar.com.flexia.scms.services.*;


@RestController
@RequestMapping(path = "/application")
public class ApplicationController {

	private ApplicationService service;
	private ApplicationRepository repo;
	
	private EnvironmentRepository envRepo;
	private EnvironmentService envService;
	
	public ApplicationController(ApplicationService appService, ApplicationRepository appRepo, EnvironmentRepository envRepo, EnvironmentService envService) {
		super();
		this.service = appService;
		this.repo = appRepo;
		this.envRepo = envRepo;
		this.envService = envService;
	}
	
	@Secured({"ADMIN"})
	@PostMapping
	public Application createApplication(@RequestBody NewApplication request) {
		return service.createApplication(request);
	}
	
	@Secured({ "ADMIN" })
	@GetMapping(path = "/{applicationId}")
	public Application getApplication(@PathVariable ("applicationId") Long applicationId) {
		return service.findById(applicationId);
	}

	@Secured({"ADMIN"})
	@GetMapping
	public List<Application> getApplications() {
		return service.findAll();
	}

	///////////////////// SECCIÓN ENVIRONMENT /////////////////////
	
	@Secured({"ADMIN"})
	@PostMapping(path = "/{applicationId}/environments")
	@Transactional
	public void addEnvironment(@PathVariable("applicationId") long appId, @RequestBody List<Environment> request) {
		
		Application a = repo.findById(appId).orElseThrow(NoSuchElementException::new);
				
		List<Long> ids = request.stream().map(Environment::getId).collect(Collectors.toList());
		
		envRepo.findAllById(ids).forEach(p -> a.addEnvironment(p));
			
		repo.save(a);
	}
	
	@Secured({ "ADMIN" })
	@GetMapping(path = "/{applicationId}/environments/{environmentId}")
	@Transactional
	public Environment getEnvironment(@PathVariable("applicationId") long applicationId, @PathVariable("environmentId") long environmentId) {
		return envService.findById(environmentId);
	}
	
	@Secured({ "ADMIN" })
	@GetMapping(path = "/{applicationId}/environments")
	@Transactional
	public List<Environment> getEnvironments(@PathVariable("applicationId") long applicationId) {
		Application app = service.findById(applicationId);
		
		return app.getEnvironments();
	}
		
	///////////////////// SECCIÓN CONFIGURATION /////////////////////
		
	@Secured({ "ADMIN" })
	@GetMapping(path = "/{applicationId}/environments/{environmentId}/configurations")
	@Transactional
	public List<Configuration> getConfigurations(@PathVariable("applicationId") long applicationId, @PathVariable("environmentId") long environmentId, @RequestHeader("APIKey") String apiKey, @RequestHeader("APISecret") String apiSecret) {
		return envService.getConfigurations(applicationId, environmentId, apiKey, apiSecret);
	}
	
}