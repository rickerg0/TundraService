package com.tundra.controller.admin;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tundra.controller.AbstractController;
import com.tundra.response.AdminValidationResponse;
import com.tundra.security.AdminAuthentication;
import com.tundra.service.AdminSecurityService;

@SuppressWarnings("serial")
public class AbstractAdminController extends AbstractController {

	@Autowired
	private AdminSecurityService securityService;

	 public AdminSecurityService getSecurityService() {
		 return securityService;
	 }
	 
	public void validate(HttpServletResponse httpResponse, String token) {

		// validate and add new token to response
		AdminValidationResponse validationResponse = securityService.validate(token);

		SecurityContext securityCtx = SecurityContextHolder.getContext();
		securityCtx.setAuthentication(
				new AdminAuthentication(validationResponse.getUser(), validationResponse.getToken()));

		addTokenToResponseHeader(httpResponse, validationResponse.getToken());

	}

}
