package com.tundra.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import com.tundra.service.SecurityService;

@SuppressWarnings("serial")
public abstract class AbstractController implements Serializable {
	
	static final String ERROR_PREFIX = "Whoops : ";
	static final String HEADER_SECURITY_TOKEN = "X-Token";
	
	@Autowired
	private SecurityService securityService;

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	boolean validateRequest(String token) {
		try {
			return securityService.isValid(token);
		} catch (Exception e) {
			return false;
		}
	}
}
