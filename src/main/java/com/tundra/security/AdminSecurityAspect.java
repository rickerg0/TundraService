package com.tundra.security;

import static com.tundra.security.SecurityConstants.HEADER_SECURITY_TOKEN;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.tundra.response.AdminValidationResponse;
import com.tundra.service.AdminSecurityService;

@Component
@Aspect
public class AdminSecurityAspect {

	@Autowired
	private AdminSecurityService securityService;
	
    @Before(value = "@annotation(com.tundra.security.annotation.SecureAdmin) && execution(* *(..))")
    public void before(JoinPoint joinPoint) throws Throwable {

    	HttpServletResponse httpResponse = (HttpServletResponse)joinPoint.getArgs()[0]; 
    	String token = (String)joinPoint.getArgs()[1];
    
		// validate and add new token to response
		AdminValidationResponse validationResponse = securityService.validate(token);

		SecurityContext securityCtx = SecurityContextHolder.getContext();
		securityCtx.setAuthentication(
				new AdminAuthentication(validationResponse.getUser(), validationResponse.getToken()));

		httpResponse.addHeader(HEADER_SECURITY_TOKEN, validationResponse.getToken());
    
    }
    
}
