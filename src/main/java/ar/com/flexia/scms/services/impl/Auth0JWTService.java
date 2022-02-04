package ar.com.flexia.scms.services.impl;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;

import ar.com.flexia.scms.api.config.*;
import ar.com.flexia.scms.model.entity.User;
import ar.com.flexia.scms.services.*;

@Service
public class Auth0JWTService implements JWTService {

	private JWTConfig config;
	
	private JWTVerifier verifier;

	@Autowired
	public Auth0JWTService(JWTConfig config) {
		this.config = config;
		this.verifier = JWT.require(config.getAlgorithm()).build();
	}

	@Override
	public String issueToken(User user) {
		String jwt = JWT.create()
				.withSubject(user.getName())
				.withExpiresAt(Date.from(Instant.now().plusSeconds(config.getExpiration())))
				.withArrayClaim(JWTConfig.AUTHORITIES_CLAIM, new String[] {user.getProfile().name()})
				.sign(config.getAlgorithm());
		return JWTConfig.TOKEN_PREFIX + jwt;
	}
	
	@Override
	public DecodedJWT verify(String jwt) {
		jwt = jwt.replace(JWTConfig.TOKEN_PREFIX, "").trim();
		return verifier.verify(jwt);
	}

}