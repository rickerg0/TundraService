package com.tundra.controller.admin;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tundra.response.AdminValidationResponse;

@Controller 

@RequestMapping("/admin/")
public class AdminLoginController extends AbstractAdminController {

	private static final long serialVersionUID = 1L;

	@RequestMapping(value="login", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> adminLogin(HttpServletResponse httpResponse, 
			@RequestParam(value="userName") String userName, @RequestParam(value="password") String password) {

		return new ResponseEntity<AdminValidationResponse>(getSecurityService().login(userName, password),HttpStatus.OK);		
	}

}
