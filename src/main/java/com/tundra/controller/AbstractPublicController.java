package com.tundra.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.tundra.security.annotation.SecurePublic;
import com.tundra.service.SecurityService;

@SuppressWarnings("serial")
@SecurePublic
public class AbstractPublicController extends AbstractController {

	@Autowired
	private SecurityService securityService;

	public SecurityService getSecurityService() {
		return securityService;
	}
}
