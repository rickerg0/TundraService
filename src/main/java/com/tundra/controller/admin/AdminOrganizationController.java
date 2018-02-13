package com.tundra.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tundra.entity.Organization;
import com.tundra.service.OrganizationService;

@Controller 

@RequestMapping("/admin/org/")
public class AdminOrganizationController extends AbstractAdminController {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private OrganizationService organizationService;
	
	@RequestMapping(value="{id}", method=RequestMethod.GET) 
	public @ResponseBody ResponseEntity<?> getOrg(HttpServletResponse httpResponse, 
			@RequestHeader(value=HEADER_SECURITY_TOKEN) String token, @PathVariable(value="id") Integer id) {

		validateAndAddToken(httpResponse, token);
		
		return new ResponseEntity<Organization>(organizationService.findOrganization(id),HttpStatus.OK);
	}
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getOrgs(HttpServletResponse httpResponse, 
			@RequestHeader(value=HEADER_SECURITY_TOKEN) String token) {

		validateAndAddToken(httpResponse, token);

		return new ResponseEntity<List<Organization>>(organizationService.findAllOrganizations(),HttpStatus.OK);
		
	}
	
	@RequestMapping(value="name/{name}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getOrgByName(HttpServletResponse httpResponse, 
			@RequestHeader(value=HEADER_SECURITY_TOKEN) String token, @PathVariable(value="name") String name) {

		validateAndAddToken(httpResponse, token);

		return new ResponseEntity<List<Organization>>(organizationService.findByName(name),HttpStatus.OK);
	}
}
