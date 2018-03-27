package com.tundra.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tundra.dao.OrganizationDAO;
import com.tundra.entity.Location;
import com.tundra.entity.Organization;
import com.tundra.entity.User;
import com.tundra.entity.UserAuthority;
import com.tundra.security.AdminAuthentication;
import com.tundra.security.Authority;
import com.tundra.security.service.AdminSecurityService;

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
		
		Set<Location> locs = new HashSet<>();
		
		for (Organization org: orgs) {
			locs.addAll(org.getLocationSet());
		}
		
		//user.setLocations(new HashSet<>(user.getOrganization().getLocationSet()));
		user.setLocations(locs);
		
		Set<UserAuthority> auths = new HashSet<>();
		
		auths.add(createAuth(user, Authority.READ_TAG));
		auths.add(createAuth(user, Authority.UPDATE_TAG));
		auths.add(createAuth(user, Authority.DELETE_TAG));
		auths.add(createAuth(user, Authority.READ_ORGANIZATION));
		
		user.setUserAuthorities(auths);
		
		return user;
		
	}
	
	private UserAuthority createAuth(User user, Authority auth) {

		UserAuthority userauth = new UserAuthority();
		userauth.setAuthority(auth.name());
		userauth.setUser(user);
		return userauth;
		
	}
	
	protected void setSecurityContext() {
		SecurityContext securityCtx = SecurityContextHolder.getContext();
		AdminAuthentication auth = new AdminAuthentication(getUser(), "fauxtoken");
		securityCtx.setAuthentication(auth);
	}

	
}
