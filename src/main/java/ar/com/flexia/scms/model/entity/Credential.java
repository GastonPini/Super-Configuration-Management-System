package ar.com.flexia.scms.model.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Credential {
	
	@Id
	private Long id;
	
	private String apiKey;
	
	@JsonIgnore
	private String apiSecret;
	
	@Transient
	private String apiSecretI;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Environment environment;
	
	public Credential() {
		this.apiKey = UUID.randomUUID().toString();
		this.apiSecret = UUID.randomUUID().toString();
	}
	
	public Credential(String aPIKey, String aPISecret, String aPISecretI) {
		this.apiKey = aPIKey;
		this.apiSecret = aPISecret;
		this.apiSecretI = aPISecretI;
	}

	public String getAPIKey() {
		return apiKey;
	}
	
	@JsonIgnore
	public String getAPISecret() {
		return apiSecret;
	}

	public String getAPISecretI() {
		return apiSecretI;
	}

	@JsonIgnore
	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

}