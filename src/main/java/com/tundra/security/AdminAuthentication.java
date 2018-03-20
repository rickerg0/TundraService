package com.tundra.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.tundra.entity.User;
import com.tundra.entity.UserAuthority;

public class AdminAuthentication implements Authentication {

	private static final long serialVersionUID = 1L;

	private User user;
	private String token;
	private Collection<Authority> authoritiyStringList = new ArrayList<>();
	
	public AdminAuthentication(User user, String token) {
		super();
		this.user = user;
		this.token = token;
		
		if (this.user != null && this.user.getUserAuthorities() != null && !this.user.getUserAuthorities().isEmpty()) {
			for (UserAuthority auth: user.getUserAuthorities()) {
				authoritiyStringList.add(Authority.valueOf(auth.getAuthority()));
			}
		}
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
		return user.getUserAuthorities();
	}

	public Collection<Authority> getAuthoritityList() {
		return authoritiyStringList;
	}

	@Override
	public Object getCredentials() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object getDetails() {
		throw new UnsupportedOperationException();
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
		if (user == null) {
			throw new SecurityException("User not authenticated");
		}
	}
}
