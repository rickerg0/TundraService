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

	private static final String ERROR_PREFIX = "Whoops : ";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private TundraService tundraService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) 
	public @ResponseBody ResponseEntity<?> getOrg(HttpServletResponse httpResponse, @PathVariable(value="id") Integer id) {
		try { 
			return new ResponseEntity<Organization>(tundraService.findOrganization(id),HttpStatus.OK);
		} catch (Throwable t) {
			return new ResponseEntity<String>(ERROR_PREFIX + t.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getOrgs(HttpServletResponse httpResponse) {
		try {
			return new ResponseEntity<List<Organization>>(tundraService.findAllOrganizations(),HttpStatus.OK);
		} catch (Throwable t) {
			return new ResponseEntity<String>(ERROR_PREFIX + t.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/name/{name}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getOrgByName(HttpServletResponse httpResponse, @PathVariable(value="name") String name) {
		try {
			return new ResponseEntity<List<Organization>>(tundraService.findByName(name),HttpStatus.OK);
		} catch (Throwable t) {
			return new ResponseEntity<String>(ERROR_PREFIX + t.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}

	@RequestMapping(value="/name/{name}/{city}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getOrgByNameAndCity(HttpServletResponse httpResponse, @PathVariable(value="name") String name, @PathVariable(value="city") String city) {
		try {
			return new ResponseEntity<List<Organization>>(tundraService.findByNameAndCity(name,city),HttpStatus.OK);
		} catch (Throwable t) {
			return new ResponseEntity<String>(ERROR_PREFIX + t.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
