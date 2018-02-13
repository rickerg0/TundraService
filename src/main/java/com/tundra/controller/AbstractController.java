package com.tundra.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public abstract class AbstractController implements Serializable {
	
	static final String ERROR_PREFIX = "Whoops : ";

	public static final String HEADER_SECURITY_TOKEN = "X-AUTH-TOKEN";
	
	protected void addTokenToResponseHeader(HttpServletResponse httpResponse, String token) {
		httpResponse.addHeader(HEADER_SECURITY_TOKEN, token);
	}
	
}
