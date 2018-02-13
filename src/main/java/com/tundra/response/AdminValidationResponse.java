package com.tundra.response;

import com.tundra.entity.User;

public class AdminValidationResponse {
	
	private User user;
	private String token;

	public AdminValidationResponse(String token, User user) {
		setToken(token);
		setUser(user);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
