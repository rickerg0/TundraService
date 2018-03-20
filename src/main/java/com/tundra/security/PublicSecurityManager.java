package com.tundra.security;

import static com.tundra.security.SecurityConstants.HEADER_SECURITY_TOKEN;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.tundra.security.service.SecurityService;

@Component("publicSecurityManager")
public class PublicSecurityManager {

	@Autowired
	private SecurityService securityService;

	public boolean isValidUser(HttpServletResponse httpResponse, String token) {

    	String newToken = securityService.validate(token);
		String email = securityService.getEmail(newToken);
		
		SecurityContext securityCtx = SecurityContextHolder.getContext();
		securityCtx.setAuthentication(new PublicAuthentication(email, newToken));
		
		httpResponse.addHeader(HEADER_SECURITY_TOKEN, newToken);

		return true;
	}

}
