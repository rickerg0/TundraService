package com.tundra.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tundra.dao.UserDAO;
import com.tundra.entity.User;
import com.tundra.exception.ExpiredTokenException;
import com.tundra.exception.InvalidTokenException;

@Service
public class AdminSecurityServiceImpl extends AbstractSecurityService implements AdminSecurityService {

	private static final String INVALID_LOGIN = "Invalid login";

	private static final long EXPIRE_MILIS = TimeUnit.MINUTES.toMillis(10);

	@Autowired
	UserDAO userDAO;

	@Override
	public void validate(String token) {

		User user = doValidate(token);
		
		if (user == null) {
			throw new SecurityException(INVALID_LOGIN);
		}
		
	}

	@Override
	public String login(String userName, String password) throws SecurityException {
		 // TODO: Placeholder. This needs to be thought out a bit. 
		
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			throw new SecurityException(INVALID_LOGIN);
		}
		
		String encryptedPassword = encode(password);
		List<User> users = userDAO.findByUserNameAndPassword(userName, encryptedPassword);
		if (users == null || users.isEmpty() || users.size() > 1) {
			throw new SecurityException(INVALID_LOGIN);
		}
		
		// return the encrypted payload inside the signed token
		return createSignedToken(encode(createToken(users.get(0))));
		
	}

	private String createToken(User user) {
		// remove nulls and add delimiters
		// just use the user id in the payload... dave sartory's idea
		String payload = null;
		if (user != null && user.getId() != null) {
			payload = UUID.randomUUID().toString() + DELIMITER + // just push the date over one place so we always know where it is 
					new SimpleDateFormat(DATETIME_FORMAT).format(new Date()) + DELIMITER + user.getId();  //TODO: change to timestamp
		}
		
		return payload;
		
	}

	@Override
	public String renew(String token) {
		User user = doValidate(token);

		if (user == null) {
			throw new SecurityException(INVALID_LOGIN);
		}
		
		return createToken(user);
	}
	
	private User doValidate(String token) {
		// check the date... it's always in the first position
		try {
			token = getPayload(token);
			if (!isValid(token, EXPIRE_MILIS)) {
				throw new ExpiredTokenException("Expired token: " + token);
			}
		} catch (Exception e) {
			throw new InvalidTokenException("Invalid token: " + token);
		}
		
		// now make sure the user is valid
		// decrypt it - token ends up as UUID^^DATE_TIME^^ID
		User user = null;

		try {
			String source = decode(token);
			String[] sourceElements = StringUtils.split(source, DELIMITER);
	
			// TODO: finish this... needs to be thought out a bit more
			String id = sourceElements[2];
			if (StringUtils.isNoneBlank(id)) {
				user = userDAO.findOne(Integer.parseInt(id));
			}
		} catch (Exception e) {
			// just set the user to null and fall through to an invalid login 
			user = null;
		}
		
		return user;
	}	
	
}
