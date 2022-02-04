package ar.com.flexia.scms.api.dto;

import ar.com.flexia.scms.model.entity.User;

public class Session {

	private String token;
	
	private User user;

	public Session(String token, User user) {
		super();
		this.token = token;
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public User getUser() {
		return user;
	}

}