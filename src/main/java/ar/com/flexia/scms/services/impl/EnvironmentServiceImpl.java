package ar.com.flexia.scms.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ar.com.flexia.scms.api.dto.*;
import ar.com.flexia.scms.model.entity.*;
import ar.com.flexia.scms.model.repository.*;
import ar.com.flexia.scms.services.*;


@Service
public class EnvironmentServiceImpl implements EnvironmentService {
	
	private final EnvironmentRepository repo;
	
	private final ApplicationRepository appRepo;
	
	private ApplicationService appService;
	
	private CredentialService credService;
	
	private PasswordEncoder encoder;
	
	public EnvironmentServiceImpl(EnvironmentRepository repo, ApplicationRepository appRepo, ApplicationService appService, CredentialService credService, PasswordEncoder encoder) {
		super();
		this.repo = repo;
		this.appRepo = appRepo;
		this.encoder = encoder;
		this.credService = credService;
		this.appService = appService;
	}
	
	@Override
	public Environment createEnvironment(NewEnvironment newEnvironment, NewApplication newApplication) {
		
		Optional<Environment> env = repo.findByNameAndApplication(newEnvironment.getName(), appRepo.findByName(newApplication.getName()));
		
		if(env.isPresent())
			throw new IllegalArgumentException("El ambiente " + newEnvironment.getName() + " ya existe para " + newEnvironment.getApplication().getName() + ". Ingrese otro nombre.");

		else {
		
			Application app;
			
			if(!appRepo.findByName(newApplication.getName()).isPresent()) {
				app = appService.createApplication(newApplication);
				return repo.save(new Environment(newEnvironment.getName(), newEnvironment.getDescription(), app, credService.createCredential()));
			}
			
			else {
				app = appRepo.findByName(newApplication.getName()).get();
				return repo.save(new Environment(newEnvironment.getName(), newEnvironment.getDescription(), app, credService.createCredential()));
			}
			
		}
		
	}
		
	@Transactional
	@Override
	public List<Environment> findAll() {
		return repo.findAll();
	}
	
	@Transactional
	@Override
	public Environment findById(long environmentId) {
		return repo.findById(environmentId)
				.orElseThrow(() -> new APIException("Ambiente no encontrado"));
	}
	
	///////////////////// SECCIÓN CONFIGURATION /////////////////////
	
	@Transactional
	@Override
	public Environment addConfiguration(long environmentId, Configuration newConfiguration) {
		Environment env = repo.findById(environmentId).orElseThrow(NoSuchElementException::new);
		env.addConfiguration(newConfiguration);
		return repo.save(env);
	}
	
	@Transactional
	@Override
	public Environment removeConfiguration(long environmentId, long configurationId) {
		Environment env = repo.findById(environmentId).orElseThrow(NoSuchElementException::new);
		Configuration configToRemove = env.getConfigurations().stream()
			.filter(config -> config.getId() == configurationId)
			.findFirst().orElseThrow(NoSuchElementException::new);
		env.removeConfiguration(configToRemove);
		return repo.save(env);
	}
	
	@Override
	public List<Configuration> getConfigurations(long applicationId, long environmentId, String apiKey, String apiSecret) {
		
		Environment env = repo.findById(environmentId).orElseThrow(() -> new Error("Ambiente no encontrado."));
		
		if(applicationId != env.getApplication().getId())
//		if(applicationId != env.getApplication().getId()) // ACA
			throw new BadCredentialsException("Ambiente no encontrado");
		
		if(!(apiKey.equals(env.getCredential().getAPIKey()) && encoder.matches(apiSecret, env.getCredential().getAPISecret()))) {
			
			throw new BadCredentialsException("Credenciales inválidas");
		}
		
		return env.getConfigurations();			
	
	}

	
		
}