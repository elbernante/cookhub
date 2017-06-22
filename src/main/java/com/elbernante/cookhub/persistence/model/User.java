package com.elbernante.cookhub.persistence.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	@JsonIgnore
	private String username;
	
	@Column(unique=true)
	@JsonIgnore
	private String facebookId;
	
	@JsonIgnore
	private String password;
	
	private String displayName;
	
	@OneToMany(mappedBy="author")
	@JsonBackReference
	private List<Recipe> recipes = new ArrayList<>();
	
	public User() {}
	
	public User(FacebookUser fbUser) {
		updateWith(fbUser);
	}
	
	public void updateWith(FacebookUser fbUser) {
		facebookId = fbUser.getId();
		displayName = fbUser.getFirst_name() + fbUser.getLast_name();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}
	
	public String getFacebookId() {
		return facebookId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	
	
	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
        builder.append("User {id=").append(id)
               .append(", username=").append(username)
               .append(", password=").append(password).append("}");
        return builder.toString();
	}

}
