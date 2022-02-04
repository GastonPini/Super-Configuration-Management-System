package ar.com.flexia.scms.api.controllers;

import java.util.List;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexia.scms.api.dto.*;
import ar.com.flexia.scms.model.entity.*;
import ar.com.flexia.scms.services.*;


@RestController
@RequestMapping(path = "/users")
public class UserController {

	private UserService service;

	public UserController(UserService service) {
		super();
		this.service = service;
	}
	
	@Secured({ "ADMIN" })
	@PostMapping
	public User createUser(@RequestBody NewUser request) {
		return service.createUser(request);
	}

	@GetMapping(path = "/{userId}")
	public User getUser(@PathVariable("userId") Long userId) {
		return service.findById(userId);
	}
	
	@Secured({ "ADMIN" })
	@GetMapping
	public List<User> getUsers() {
		return service.findAll();
	}
	
	@Secured({"ADMIN"})
	@DeleteMapping(path = "/{userId}")
	public void deleteUser(@PathVariable (value = "userId") Long userId) {
		service.deleteUser(userId);
	}
	
}