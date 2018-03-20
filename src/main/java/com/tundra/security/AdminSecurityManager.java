package com.tundra.security;

import static com.tundra.security.SecurityConstants.HEADER_SECURITY_TOKEN;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.tundra.response.AdminValidationResponse;
import com.tundra.service.AdminSecurityService;

@Component("adminSecurityManager")
public class AdminSecurityManager {

	@Autowired
	private AdminSecurityService securityService;

	public boolean isValidAdminUser(HttpServletResponse httpResponse, String token) {

		// validate and add new token to response
		AdminValidationResponse validationResponse = securityService.validate(token);

		SecurityContext securityCtx = SecurityContextHolder.getContext();
		securityCtx.setAuthentication(
				new AdminAuthentication(validationResponse.getUser(), validationResponse.getToken()));

		httpResponse.addHeader(HEADER_SECURITY_TOKEN, validationResponse.getToken());
		
		return true;
	}

	public boolean hasAuthority(Authority authority) {
		
		AdminAuthentication auth = (AdminAuthentication)SecurityContextHolder.getContext().getAuthentication();
		System.err.println("Checking authority: " + authority);
		return auth.getAuthoritityList().contains(authority);
	}
}
