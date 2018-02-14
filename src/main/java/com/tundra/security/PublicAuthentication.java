package com.tundra.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class PublicAuthentication implements Authentication {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String token;
	
	public PublicAuthentication(String userName, String token) {
		super();
		this.userName = userName;
		this.token = token;
	}

	@Override
	public String getName() {
		return userName;
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
	public Object getPrincipal() {
		return userName;
	}

	@Override
	public boolean isAuthenticated() {
		return (userName != null && token != null);
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		// no op.  authentication is determined based on user and token existence

	}

}
