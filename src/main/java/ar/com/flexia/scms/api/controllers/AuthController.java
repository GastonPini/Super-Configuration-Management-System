package ar.com.flexia.scms.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.flexia.scms.api.dto.*;
import ar.com.flexia.scms.services.UserService;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

	private UserService service;
	
	public AuthController(UserService userService) {
		super();
		this.service = userService;
	}

	@PostMapping(path = "/login")
	public Session login(@RequestBody AdminCredentials creds) {
		return service.login(creds);
	}
	
}