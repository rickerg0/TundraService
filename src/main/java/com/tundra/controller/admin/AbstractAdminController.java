package com.tundra.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;

import com.tundra.controller.AbstractController;
import com.tundra.security.annotation.SecureAdmin;
import com.tundra.service.AdminSecurityService;

@SuppressWarnings("serial")
@SecureAdmin
public class AbstractAdminController extends AbstractController {

	@Autowired
	private AdminSecurityService securityService;

	 public AdminSecurityService getSecurityService() {
		 return securityService;
	 }
	 
}
