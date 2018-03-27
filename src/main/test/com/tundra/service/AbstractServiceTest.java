package com.tundra.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.tundra.dao.UserDAO;
import com.tundra.entity.User;
import com.tundra.security.service.AdminSecurityService;
import com.tundra.test.AbstractTest;

public class AbstractServiceTest extends AbstractTest {
	
	static final String PASSWORD = "password";
	
	@Autowired 
	AdminSecurityService adminSecurityService;
	
	@Autowired 
	UserDAO userDAO;
	
	
	User adminUser;

}
