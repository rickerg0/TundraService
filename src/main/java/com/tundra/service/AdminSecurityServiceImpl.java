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

@Service
public class AdminSecurityServiceImpl extends AbstractSecurityService implements AdminSecurityService {

	private static final String INVALID_LOGIN = "Invalid login";

	private static final long EXPIRE_MILIS = TimeUnit.MINUTES.toMillis(10);

	@Autowired
	UserDAO userDAO;

	@Override
	public void validate(String token) {
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
		return createSignedToken(encode(createAdminToken(users.get(0))));
		
	}

}
