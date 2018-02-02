package com.tundra.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tundra.dao.UserDAO;
import com.tundra.entity.User;
import com.tundra.exception.ExpiredTokenException;
import com.tundra.exception.InvalidTokenException;
import com.tundra.util.SecurityUtil;

@Service
public class AdminSecurityServiceImpl implements AdminSecurityService {

	private static final String INVALID_LOGIN = "Invalid login";

	private static final long EXPIRE_MILIS = TimeUnit.MINUTES.toMillis(10);

	@Autowired
	UserDAO userDAO;

	@Override
	public void validate(String token) {
		// check the date... it's always in the first position
		try {
			token = SecurityUtil.getPayload(token);
			if (!SecurityUtil.isValid(token, EXPIRE_MILIS)) {
				throw new ExpiredTokenException("Expired token: " + token);
			}
		} catch (Exception e) {
			throw new InvalidTokenException("Invalid token: " + token);
		}
		
		// now make sure the user is valid
		// decrypt it - token ends up as UUID^^DATE_TIME^^FIRST_NAME^^LAST_NAME^^EMAIL^^USER_NAME

		String source = SecurityUtil.decode(token);
		String[] sourceElements = StringUtils.split(source, SecurityUtil.DELIMITER);

		// TODO: finish this... needs to be thought out a bit more
		String firstName = sourceElements[2];
		String lastName = sourceElements[3];
		String email = sourceElements[4];
		String userName = sourceElements[5];
		
		List<User> users = userDAO.findByFirstNameAndLastNameAndEmailAndUserName(firstName, lastName, email, userName);
		if (users == null || users.isEmpty() || users.size() > 1) {
			throw new SecurityException(INVALID_LOGIN);
		}
		
	}

	@Override
	public String login(String userName, String password) throws SecurityException {
		 // TODO: Placeholder. This needs to be thought out a bit. 
		
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			throw new SecurityException(INVALID_LOGIN);
		}
		
		String encryptedPassword = SecurityUtil.encode(password);
		List<User> users = userDAO.findByUserNameAndPassword(userName, encryptedPassword);
		if (users == null || users.isEmpty() || users.size() > 1) {
			throw new SecurityException(INVALID_LOGIN);
		}
		
		// return the encrypted payload inside the signed token
		return SecurityUtil.createSignedToken(
				SecurityUtil.encode(SecurityUtil.createAdminToken(users.get(0))));
		
	}

}
