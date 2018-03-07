package com.tundra.controller.admin;

import static com.tundra.security.SecurityConstants.HEADER_SECURITY_TOKEN;

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

import com.tundra.controller.AbstractController;
import com.tundra.entity.Organization;
import com.tundra.security.annotation.SecureAdmin;
import com.tundra.service.OrganizationService;

@Controller 

@RequestMapping("/admin/org/")
public class AdminOrganizationController extends AbstractController {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private OrganizationService organizationService;
	
	@SecureAdmin
	@RequestMapping(value="{id}", method=RequestMethod.GET) 
	public @ResponseBody ResponseEntity<?> getOrg(HttpServletResponse httpResponse, 
			@RequestHeader(value=HEADER_SECURITY_TOKEN) String token, @PathVariable(value="id") Integer id) {

		return new ResponseEntity<Organization>(organizationService.findOrganization(id),HttpStatus.OK);
	}
	
	@SecureAdmin
	@RequestMapping(value="list", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getOrgs(HttpServletResponse httpResponse, 
			@RequestHeader(value=HEADER_SECURITY_TOKEN) String token) {

		return new ResponseEntity<List<Organization>>(organizationService.findAllOrganizations(),HttpStatus.OK);
	}
	
	@SecureAdmin
	@RequestMapping(value="name/{name}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getOrgByName(HttpServletResponse httpResponse, 
			@RequestHeader(value=HEADER_SECURITY_TOKEN) String token, @PathVariable(value="name") String name) {

		return new ResponseEntity<List<Organization>>(organizationService.findByName(name),HttpStatus.OK);
	}
}
