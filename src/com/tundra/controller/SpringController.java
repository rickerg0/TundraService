package com.tundra.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tundra.database.Organization;

@Controller 

@RequestMapping("/test")
public class SpringController implements  Serializable {

	@RequestMapping(value="/", method=RequestMethod.GET) 
	public @ResponseBody ResponseEntity<?> getOrg(HttpServletResponse httpResponse) {
		
		return new ResponseEntity<Organization>(new Organization(),HttpStatus.OK);
	}
}
