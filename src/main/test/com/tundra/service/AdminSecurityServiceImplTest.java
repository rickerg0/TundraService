package com.tundra.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tundra.dao.UserDAO;
import com.tundra.entity.User;
import com.tundra.springconfig.ApplicationConfig;
import com.tundra.test.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
@Transactional // so tests roll back
public class AdminSecurityServiceImplTest extends AbstractTest {

	private static final String PASSWORD = "password";

	@Autowired 
	private AdminSecurityService adminSecurityService;
	
	@Autowired 
	private UserDAO userDAO;
	
	
	private User adminUser;
	
	@Before
	public void doBefore() {
	
		adminUser = getUser();
	}
	
	@Test(expected = SecurityException.class)
	public void adminTest() {
		
		userDAO.save(adminUser);
		String token = adminSecurityService.login(USER_NAME, PASSWORD);
		
		adminSecurityService.validate(token);
		
		token = adminSecurityService.renew(token);
		
		// test invalid login
		adminSecurityService.login(USER_NAME, "whatever");
		
	}
	
}
