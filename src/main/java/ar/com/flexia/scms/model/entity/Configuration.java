package ar.com.flexia.scms.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Configuration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_configuration")
	private long id;
	
	private String clave;
	
	private String valor;
	
	public Configuration() {}
	
	public Configuration(String clave, String valor, Environment environment) {
		this.clave = clave;
		this.valor = valor;
		this.environment = environment;
	}

	public long getId() {
		return id;
	}
	
	public String getClave() {
		return clave;
	}

	public String getValor() {
		return valor;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	///////////////////// SECCIÓN ENVIRONMENT /////////////////////
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Environment environment;

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	public String getApiKey() {
		return this.environment.getCredential().getAPIKey();
	}
	
	public String getApiSecret() {
		return this.environment.getCredential().getAPISecretI();
	}
	
}