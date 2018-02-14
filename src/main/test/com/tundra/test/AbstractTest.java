package com.tundra.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tundra.dao.OrganizationDAO;
import com.tundra.entity.Organization;
import com.tundra.entity.User;
import com.tundra.service.AdminSecurityService;

public class AbstractTest {
	
	protected static final String CREATE_USER = "testAdmin";
	protected static final String PASSWORD = "password";
	protected static final String EMAIL = "test.admin@whatever.x";
	protected static final String LAST_NAME = "admin";
	protected static final String USER_NAME = "admin";
	protected static final String FIRST_NAME = "test";

	@Autowired 
	private AdminSecurityService adminSecurityService;
	
	@Autowired
	private OrganizationDAO organizationDAO;

	protected User getUser() {
		User user = new User();
		
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		user.setEmail(EMAIL);
		user.setUserName(USER_NAME);
		user.setPassword(adminSecurityService.encode(PASSWORD));
		
		List<Organization>orgs = organizationDAO.findAll();
		user.setOrganization(orgs.get(0));
		
		return user;
		
	}
}
