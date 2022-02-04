package ar.com.flexia.scms.api.controllers;

import java.util.List;
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
import ar.com.flexia.scms.model.entity.Configuration;
import ar.com.flexia.scms.services.*;


@RestController
@RequestMapping(path = "/configuration")
public class ConfigurationController {

	private ConfigurationService service;

	public ConfigurationController(ConfigurationService service) {
		super();
		this.service = service;
	}

	@Secured({ "ADMIN" })
	@PostMapping
	public Configuration createConfiguration(@RequestBody NewConfiguration request) {
		
		NewEnvironment e = new NewEnvironment(request.getEnvironment());
		NewApplication a = new NewApplication(request.getEnvironment().getApplication());
		
		return service.createConfiguration(request, e, a);
	}
	
	@Secured({ "ADMIN" })
	@GetMapping(path = "/{configurationId}")
	public Configuration getConfiguration(@PathVariable("configurationId") long configurationId) {
		return service.findById(configurationId);
	}
	
	@Secured({"ADMIN"})
	@GetMapping
	public List<Configuration> getConfigurations() {
		return service.findAll();
	}
	
	// EDICIÓN DE CONFIGURATION DESDE DIRECCIÓN /configuration
	@Secured({"ADMIN"})
	@PutMapping(path = "/{configId}")
	public void editConfiguration(@PathVariable ("configId") long configId, @RequestBody NewConfiguration newValue) {
		service.updateConfiguration(configId, newValue);
	}
	
	// ELIMINACIÓN DE CONFIGURATION DESDE DIRECCIÓN /configuration
	@Secured({"ADMIN"})
	@DeleteMapping(path = "/{configId}")
	public void deleteConfiguration(@PathVariable("configId") long configId) {
		service.deleteConfiguration(configId);
	}
	
}