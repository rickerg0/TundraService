package com.tundra.response;

import com.tundra.entity.User;

public class AdminValidationResponse extends ValidationResponse {
	
	private User user;

	public AdminValidationResponse(String token, User user) {
		super(token);
		setUser(user);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
