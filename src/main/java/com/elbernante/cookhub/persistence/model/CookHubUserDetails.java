package com.elbernante.cookhub.persistence.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class CookHubUserDetails extends org.springframework.security.core.userdetails.User {
	private static final long serialVersionUID = 8492574950179364505L;
	
	private User user;
	public CookHubUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getId().toString(), user.getPassword(), authorities);
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
}
