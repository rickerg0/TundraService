package com.tundra.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;

import com.tundra.controller.AbstractController;
import com.tundra.service.AdminSecurityService;

@SuppressWarnings("serial")
public class AbstractAdminController extends AbstractController{

	public static final String HEADER_SECURITY_TOKEN = "X-Token";
	
	@Autowired
	private AdminSecurityService securityService;

	public AdminSecurityService getSecurityService() {
		return securityService;
	}

}
