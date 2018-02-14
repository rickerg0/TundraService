package com.tundra.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.tundra.entity.User;

public class AdminAuthentication implements Authentication {

	private static final long serialVersionUID = 1L;

	private User user;
	private String token;
	
	public AdminAuthentication(User user, String token) {
		super();
		this.user = user;
		this.token = token;
	}

	@Override
	public String getName() {
		if (isAuthenticated()) {
			return user.getUserName();
		}
		return null;
	}
	
	public String getToken() {
		return token;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public User getPrincipal() {
		return user;
	}

	@Override
	public boolean isAuthenticated() {
		return (user != null && token != null);
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		// no op.  authentication is determined based on user and token existence

	}

}
