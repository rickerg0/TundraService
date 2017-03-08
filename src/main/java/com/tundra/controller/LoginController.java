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
	public @ResponseBody ResponseEntity<?> login(HttpServletResponse httpResponse, 
																	@RequestParam(value="firstName") String firstName,
																	@RequestParam(value="lastName") String lastName,
																	@RequestParam(value="email") String email) {
		try {
			return new ResponseEntity<String>("{\"token\":\"" + getSecurityService().getToken(firstName, lastName, email) + "\"}",HttpStatus.OK);
		} catch (Throwable t) {
			return getErrorResponseEntity(t);
		}
	}
}
