package ar.com.flexia.scms.services.impl;

import java.util.List;
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
public class UserServiceImpl implements UserService {

	private PasswordEncoder encoder;
	
	private UserRepository repo;
	
	private JWTService jwtService;
	
	public UserServiceImpl(PasswordEncoder encoder, JWTService jwt, UserRepository user) {
		super();
		this.encoder = encoder;
		this.jwtService = jwt;
		this.repo = user;
		
		if (0 == this.repo.count()) {
			repo.save(new User("admin", encoder.encode("admin"), UserProfile.ADMIN));
		}
	}
	
	@Transactional
	@Override
	public User createUser(NewUser newUser) {
		
		if(repo.findByName(newUser.getName()).isPresent())
			throw new IllegalArgumentException("El usuario " + newUser.getName() + " ya existe, ingrese otro nombre.");
		
		return repo.save(new User(newUser.getName(), newUser.getPassword(), UserProfile.ADMIN));
	}
	
	@Override
	public Optional<User> findUser(String username) {
		return repo.findByName(username);
	}
	
	@Override
	public User findById(long userId) {
		return repo.findById(userId)
				.orElseThrow(() -> new APIException("Usuario no encontrado,"));
	}
	
	@Override
	public List<User> findAll() {
		return repo.findAll();
	}
	
	@Override
	public void deleteUser(long userId) {
		repo.deleteById(userId);		
	}
	
	@Override
	public Session login(AdminCredentials credentials) {
		User user = repo.findByName(credentials.getName())
				.orElseThrow(() -> new BadCredentialsException("Credenciales inválidas"));
		
		if (!encoder.matches(credentials.getPassword(), user.getPassword())) {
			throw new BadCredentialsException("Credenciales inválidas");
		}
		
		String token = jwtService.issueToken(user);
		return new Session(token, user);
	}
	
}