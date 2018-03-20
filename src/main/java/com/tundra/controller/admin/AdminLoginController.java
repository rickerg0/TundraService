package com.tundra.controller.admin;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tundra.controller.AbstractController;
import com.tundra.response.AdminValidationResponse;
import com.tundra.security.service.AdminSecurityService;

@Controller 

@RequestMapping("/admin/")
public class AdminLoginController extends AbstractController {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AdminSecurityService securityService;

	@RequestMapping(value="login", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> adminLogin(HttpServletResponse httpResponse, 
			@RequestParam(value="userName") String userName, @RequestParam(value="password") String password) {

		return new ResponseEntity<AdminValidationResponse>(securityService.login(userName, password),HttpStatus.OK);		
	}

}
