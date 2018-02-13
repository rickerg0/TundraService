package com.tundra.controller.interceptor;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@SuppressWarnings("serial")
public abstract class AbstractInterceptor extends HandlerInterceptorAdapter implements Serializable {
	
	static final String ERROR_PREFIX = "Whoops : ";

	public static final String HEADER_SECURITY_TOKEN = "X-Token";
	
	protected void addTokenToResponseHeader(HttpServletResponse httpResponse, String token) {
		httpResponse.addHeader(HEADER_SECURITY_TOKEN, token);
	}
	
}
