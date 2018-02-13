package com.tundra.controller.admin;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.tundra.controller.AbstractController;
import com.tundra.response.AdminValidationResponse;
import com.tundra.service.AdminSecurityService;

@SuppressWarnings("serial")
public class AbstractAdminController extends AbstractController{

	@Autowired
	private AdminSecurityService securityService;

	public AdminSecurityService getSecurityService() {
		return securityService;
	}
	
	AdminValidationResponse validateAndAddToken(HttpServletResponse httpResponse, String token) {
		
		AdminValidationResponse validationResponse = getSecurityService().validate(token);
		addTokenToResponseHeader(httpResponse, validationResponse.getToken());
		return validationResponse;
	}

}
