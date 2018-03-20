package com.tundra.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tundra.security.PublicAuthentication;
import com.tundra.security.service.SecurityService;

import static com.tundra.security.SecurityConstants.HEADER_SECURITY_TOKEN;


@Controller 
@RequestMapping("/")
public class LoginController extends AbstractController {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SecurityService securityService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="login", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> login(HttpServletResponse httpResponse, @RequestParam(value="email") String email) {
		
		String token = securityService.getToken(email);
		addTokenToResponseHeader(httpResponse, token);
		
		return new ResponseEntity(HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="register", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> register(HttpServletResponse httpResponse, 
			@RequestParam(value="email") String email,
			@RequestParam(value="firstName") String firstName,
			@RequestParam(value="lastName") String lastName,
			@RequestParam(value="deviceId") String deviceId,
			@RequestParam(value="platform") String platform) {
		
		// one time security context with no token to save the registration record
		SecurityContext securityCtx = SecurityContextHolder.getContext();
		securityCtx.setAuthentication(new PublicAuthentication(email, null));
		
		securityService.register(email, firstName, lastName, deviceId, platform);

		addTokenToResponseHeader(httpResponse, securityService.getToken(email));
		
		return new ResponseEntity(HttpStatus.OK);
	}

	private void addTokenToResponseHeader(HttpServletResponse httpResponse, String token) {
		httpResponse.addHeader(HEADER_SECURITY_TOKEN, token);
	}
	
}
