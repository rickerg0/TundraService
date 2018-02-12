package com.tundra.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.tundra.service.SecurityService;

@SuppressWarnings("serial")
public class AbstractPublicController extends AbstractController {

	public static final String HEADER_SECURITY_TOKEN = "X-Token";
	
	@Autowired
	private SecurityService securityService;

	public SecurityService getSecurityService() {
		return securityService;
	}


}
