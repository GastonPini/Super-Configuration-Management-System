package ar.com.flexia.scms.services;

import java.util.List;
import java.util.Optional;

import ar.com.flexia.scms.api.dto.*;
import ar.com.flexia.scms.model.entity.User;


public interface UserService {

	public User createUser(NewUser newUser);
	
	public Optional<User> findUser(String name);

	public User findById(long userId);
	
	public List<User> findAll();
	
	void deleteUser(long userId);

	public Session login(AdminCredentials credentials);
	
}