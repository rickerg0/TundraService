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
public class LoginController extends AbstractController {

	private static final long serialVersionUID = 1L;

	@RequestMapping(value="login", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> login(HttpServletResponse httpResponse, @RequestParam(value="email") String email) {
		return new ResponseEntity<String>("{\"token\":\"" + getSecurityService().getToken(email) + "\"}",HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="register", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> register(HttpServletResponse httpResponse, 
			@RequestParam(value="email") String email,
			@RequestParam(value="firstName") String firstName,
			@RequestParam(value="lastName") String lastName,
			@RequestParam(value="deviceId") String deviceId,
			@RequestParam(value="platform") String platform) {
		
		getSecurityService().register(email, firstName, lastName, deviceId, platform, "registrationProcess");
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value="adminLogin", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> adminLogin(HttpServletResponse httpResponse, 
			@RequestParam(value="userName") String userName, @RequestParam(value="password") String password) {
		return new ResponseEntity<String>("{\"token\":\"" + getAdminSecurityService().login(userName, password) + "\"}",HttpStatus.OK);
	}

	
}
