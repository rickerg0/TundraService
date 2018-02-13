package com.tundra.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.tundra.service.SecurityService;

@SuppressWarnings("serial")
public class AbstractPublicController extends AbstractController {

	@Autowired
	private SecurityService securityService;

	public SecurityService getSecurityService() {
		return securityService;
	}

	void validateAndAddToken(HttpServletResponse httpResponse, String token) {
		addTokenToResponseHeader(httpResponse, getSecurityService().validate(token));
	}

}
