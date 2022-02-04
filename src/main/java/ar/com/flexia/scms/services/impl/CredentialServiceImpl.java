package ar.com.flexia.scms.services.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ar.com.flexia.scms.model.entity.*;
import ar.com.flexia.scms.model.repository.*;
import ar.com.flexia.scms.services.*;


@Service
public class CredentialServiceImpl implements CredentialService {

	private PasswordEncoder encoder;
	
	private CredentialRepository repo;
	
	public CredentialServiceImpl(PasswordEncoder encoder, CredentialRepository repo) {
		
		super();
		this.encoder = encoder;
		this.repo = repo;
		
	}
	
	@Override
	public Credential createCredential() {
		
		String apiSecretI = UUID.randomUUID().toString();
		
		String apiSecret = encoder.encode(apiSecretI);
		
		String apiKey = UUID.randomUUID().toString();
		
		return new Credential(apiKey, apiSecret, apiSecretI);

	}

	public List<Credential> findAll() {
		return repo.findAll();
	}

}