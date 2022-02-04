package ar.com.flexia.scms.model.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
	private Long id;
	
	private String name;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private UserProfile profile;

	public User() {}
	
	public User(String name, String password, UserProfile profile) {
		super();
		this.name = name;
		this.password = password;
		this.profile = profile;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	public UserProfile getProfile() {
		return profile;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

}