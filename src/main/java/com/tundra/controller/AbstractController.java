package com.tundra.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.tundra.service.AdminSecurityService;
import com.tundra.service.SecurityService;

@SuppressWarnings("serial")
public abstract class AbstractController implements Serializable {
	
	static final String ERROR_PREFIX = "Whoops : ";
	static final String HEADER_SECURITY_TOKEN = "X-Token";
	
	@Autowired
	private SecurityService securityService;

	@Autowired
	private AdminSecurityService adminSecurityService;

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
	
	public AdminSecurityService getAdminSecurityService() {
		return adminSecurityService;
	}
	
}
