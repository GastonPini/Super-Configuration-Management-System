package ar.com.flexia.scms.services;

import com.auth0.jwt.interfaces.DecodedJWT;

import ar.com.flexia.scms.model.entity.User;


public interface JWTService {
	
	public String issueToken(User user);
	
	public DecodedJWT verify(String jwt);
	
}