package com.tundra.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tundra.database.Organization;

@Controller 

@RequestMapping("/test")
public class SpringController implements  Serializable {

	@RequestMapping(value="/", method=RequestMethod.GET) 
	public @ResponseBody String getOrg(HttpServletResponse httpResponse) {
		
		return "herwrw" ; //new Organization();
	}
}
