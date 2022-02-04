package ar.com.flexia.scms.model.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Environment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_environment")
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	private String description;
	
	public Environment() {}
	
	public Environment(String name, String description, Application application) {
		super();
		this.name = name;
		this.description = description;
		this.application = application;
		this.configurations = new ArrayList<>();
		setCredential(credential);
	}
	
	public Environment(String name, String description, Application application, Credential credential) {
		super();
		this.name = name;
		this.description = description;
		this.application = application;
		this.configurations = new ArrayList<>();
		setCredential(credential);
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	///////////////////// SECCIÓN APPLICATION /////////////////////
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
	public Application application;
	
	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}
	
	///////////////////// SECCIÓN CONFIGURATION /////////////////////
	
	@OneToMany(mappedBy = "environment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Configuration> configurations;
	
	public List<Configuration> getConfigurations() {
		return configurations;
	}
	
	public void addConfiguration(Configuration configuration) {
		this.configurations.add(configuration);
		configuration.setEnvironment(this);
	}
	
	public void removeConfiguration(Configuration configuration) {
		this.configurations.remove(configuration);
		configuration.setEnvironment(null);
	}
		
	///////////////////// SECCIÓN CREDENTIALS /////////////////////

	@OneToOne(mappedBy = "environment", cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
	private Credential credential;
	
	public Credential getCredential() {
		return credential;
	}
	
	public void setCredential(Credential credential) {
		this.credential = credential;
		credential.setEnvironment(this);
	}
	
}