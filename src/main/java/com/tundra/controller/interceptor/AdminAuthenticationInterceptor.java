package com.tundra.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.tundra.controller.AbstractController;
import com.tundra.response.AdminValidationResponse;
import com.tundra.service.AdminSecurityService;

@Component
public class AdminAuthenticationInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AdminSecurityService securityService;
	
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

		// validate and add new token to response
		AdminValidationResponse validationResponse = securityService.validate(token);
		addTokenToResponseHeader(httpResponse, validationResponse.getToken());

		return true;
	}
}
