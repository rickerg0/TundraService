package com.tundra.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tundra.security.PublicAuthentication;
import com.tundra.service.SecurityService;

@SuppressWarnings("serial")
public class AbstractPublicController extends AbstractController {

	@Autowired
	private SecurityService securityService;

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void validate(HttpServletResponse httpResponse, String token) {
		String newToken = securityService.validate(token);
		String email = securityService.getEmail(newToken);
		
		SecurityContext securityCtx = SecurityContextHolder.getContext();
		securityCtx.setAuthentication(new PublicAuthentication(email, newToken));
		
		// validate and add new token to response
		addTokenToResponseHeader(httpResponse, newToken);
	}	
}
