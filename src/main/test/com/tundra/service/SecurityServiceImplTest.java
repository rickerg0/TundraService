package com.tundra.service;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tundra.dao.OrganizationDAO;
import com.tundra.dao.UserDAO;
import com.tundra.entity.Organization;
import com.tundra.entity.User;
import com.tundra.springconfig.ApplicationConfig;
import com.tundra.util.SecurityUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
@Transactional // so tests roll back
public class SecurityServiceImplTest {

	private static final String CREATE_USER = "testAdmin";
	private static final String PASSWORD = "password";
	private static final String EMAIL = "test.admin@whatever.x";
	private static final String LAST_NAME = "admin";
	private static final String USER_NAME = "admin";
	private static final String FIRST_NAME = "test";

	@Autowired 
	private SecurityService securityService;
	
	@Autowired 
	private UserDAO userDAO;
	
	@Autowired
	private OrganizationDAO organizationDAO;
	
	private static final User ADMIN_USER = new User();
	
	@Before
	public void doBefore() {
		ADMIN_USER.setFirstName(FIRST_NAME);
		ADMIN_USER.setLastName(LAST_NAME);
		ADMIN_USER.setEmail(EMAIL);
		ADMIN_USER.setUserName(USER_NAME);
		ADMIN_USER.setPassword(SecurityUtil.encode(PASSWORD));
		ADMIN_USER.setCreatedUser(CREATE_USER);
		ADMIN_USER.setUpdatedUser(CREATE_USER);
		ADMIN_USER.setCreated(new Date());
		ADMIN_USER.setUpdated(new Date());
		
		List<Organization>orgs = organizationDAO.findAll();
		ADMIN_USER.setOrganization(orgs.get(0));
		
	}
	
	@Test(expected = SecurityException.class)
	public void adminTest() {
		
		userDAO.save(ADMIN_USER);
		String token = securityService.adminLogin(USER_NAME, PASSWORD);
		
		securityService.validateAdmin(token);
		
		// test invalid login
		securityService.adminLogin(USER_NAME, "whatever");
		
	}
	
}
