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

import com.tundra.service.SecurityService;

@Component
@Aspect
public class PublicSecurityAspect {

	@Autowired
	private SecurityService securityService;

    //@Before(value = "@annotation(com.tundra.security.annotation.SecurePublic) && execution(* *(..)), && args(httpResponse, token)")
    @Before(value = "@annotation(com.tundra.security.annotation.SecurePublic) && execution(* *(..))")
    //@Before("@annotation(com.tundra.security.annotation.SecurePublic)")
    public void before(JoinPoint joinPoint) throws Throwable {
    	
    	HttpServletResponse httpResponse = (HttpServletResponse)joinPoint.getArgs()[0]; 
    	String token = (String)joinPoint.getArgs()[1];

    	String newToken = securityService.validate(token);
		String email = securityService.getEmail(newToken);
		
		SecurityContext securityCtx = SecurityContextHolder.getContext();
		securityCtx.setAuthentication(new PublicAuthentication(email, newToken));
		
		httpResponse.addHeader(HEADER_SECURITY_TOKEN, newToken);
    }
    
}
