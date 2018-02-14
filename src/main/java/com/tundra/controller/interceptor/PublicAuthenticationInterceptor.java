package com.tundra.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.tundra.controller.AbstractController;
import com.tundra.security.PublicAuthentication;
import com.tundra.service.SecurityService;

@Component
@ControllerAdvice
public class PublicAuthenticationInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SecurityService securityService;
	
	@Override
	public void afterCompletion(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object handler,
			Exception exception) throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object handler)
			throws Exception {
		
		String token = httpRequest.getHeader(AbstractController.HEADER_SECURITY_TOKEN);
		String newToken = securityService.validate(token);
		String email = securityService.getEmail(token);
		
		SecurityContext securityCtx = SecurityContextHolder.getContext();
		securityCtx.setAuthentication(new PublicAuthentication(email, newToken));
		
		// validate and add new token to response
		addTokenToResponseHeader(httpResponse, newToken);


		return true;
	}
}
