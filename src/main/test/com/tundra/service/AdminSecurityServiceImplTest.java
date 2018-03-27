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
import com.tundra.response.AdminValidationResponse;
import com.tundra.security.service.AdminSecurityService;
import com.tundra.springconfig.ApplicationConfig;
import com.tundra.test.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
@Transactional // so tests roll back
public class AdminSecurityServiceImplTest extends AbstractServiceTest {

	@Before
	public void doBefore() {
	
		adminUser = getUser();
	}
	
	@Test(expected = SecurityException.class)
	public void adminTest() {
		
		userDAO.save(adminUser);
		AdminValidationResponse response = adminSecurityService.login(USER_NAME, PASSWORD); 
		String token = response.getToken();
		
		adminSecurityService.validate(token);
		
		response = adminSecurityService.renew(token);
		token = response.getToken();
		
		// test invalid login
		adminSecurityService.login(USER_NAME, "whatever");
		
	}
	
}
