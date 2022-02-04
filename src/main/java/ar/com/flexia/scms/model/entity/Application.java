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
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Application {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_application")
	private long id;
	
	@Column(unique = true)
	private String name;
	
	private String description;
	
	public Application() {}
	
	public Application(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		this.environments = new ArrayList<>();
	}
	
	@JsonIgnore
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	///////////////////// SECCIÓN ENVIRONMENT /////////////////////

	@JsonIgnore
	@OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
	public List<Environment> environments;
	
	public List<Environment> getEnvironments() {
		return environments;
	}
	
	public void addEnvironment(Environment environment) {
		this.environments.add(environment);
		environment.setApplication(this);
	}
		
}