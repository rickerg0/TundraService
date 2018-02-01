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
	
	private static final User adminUser = new User();
	
	@Before
	public void doBefore() {
		adminUser.setFirstName(FIRST_NAME);
		adminUser.setLastName(LAST_NAME);
		adminUser.setEmail(EMAIL);
		adminUser.setUserName(USER_NAME);
		adminUser.setPassword(SecurityUtil.encode(PASSWORD));
		adminUser.setCreatedUser(CREATE_USER);
		adminUser.setUpdatedUser(CREATE_USER);
		adminUser.setCreated(new Date());
		adminUser.setUpdated(new Date());
		
		List<Organization>orgs = organizationDAO.findAll();
		adminUser.setOrganization(orgs.get(0));
		
	}
	
	@Test(expected = SecurityException.class)
	public void adminTest() {
		
		userDAO.save(adminUser);
		String token = securityService.adminLogin(USER_NAME, PASSWORD);
		
		securityService.validateAdmin(token);
		
		// test invalid login
		securityService.adminLogin(USER_NAME, "whatever");
		
	}
	
}
