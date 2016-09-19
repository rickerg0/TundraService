package com.tundra.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tundra.database.Organization;
import com.tundra.service.TundraService;

@Controller 

@RequestMapping("/test")
public class SpringController implements  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private TundraService tundraService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) 
	public @ResponseBody ResponseEntity<?> getOrg(HttpServletResponse httpResponse, @PathVariable(value="id") Integer id) {
//		public @ResponseBody Organization getOrg(HttpServletResponse httpResponse) {
	
		
//		try { 
			return new ResponseEntity<Organization>(tundraService.findOrganization(id),HttpStatus.OK);
//		} catch (Throwable t) {
//			return new ResponseEntity<String>("OOps : " + t.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
//		}
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getOrgs(HttpServletResponse httpResponse) {
		return new ResponseEntity<List<Organization>>(tundraService.findAllOrganizations(),HttpStatus.OK);
	}
}
