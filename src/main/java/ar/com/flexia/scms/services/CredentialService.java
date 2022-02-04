package ar.com.flexia.scms.services;

import java.util.List;

import ar.com.flexia.scms.model.entity.*;

public interface CredentialService {

	public Credential createCredential();
	
	public List<Credential> findAll();
	
}