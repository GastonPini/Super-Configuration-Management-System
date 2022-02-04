package ar.com.flexia.scms.api.dto;

import ar.com.flexia.scms.model.entity.Environment;

public class NewConfiguration {

	private String clave;
	
	private String valor;
	
	private Environment environment;
	
	public NewConfiguration() {
		super();
	}
	
	public String getClave() {
		return clave;
	}

	public String getValor() {
		return valor;
	}

	public Environment getEnvironment() {
		return environment;
	}
	
}