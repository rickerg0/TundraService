package com.tundra.response;

public class ValidationResponse {
	
	private String token;

	public ValidationResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
