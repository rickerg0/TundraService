package com.tundra.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller 
@RequestMapping("/")
public class LoginController extends AbstractPublicController {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="login", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> login(HttpServletResponse httpResponse, @RequestParam(value="email") String email) {
		
		String token = getSecurityService().getToken(email);
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
		
		getSecurityService().register(email, firstName, lastName, deviceId, platform);

		addTokenToResponseHeader(httpResponse, getSecurityService().getToken(email));
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
}
