package com.tundra.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tundra.dao.UserDAO;
import com.tundra.entity.User;
import com.tundra.exception.ExpiredTokenException;
import com.tundra.exception.InvalidTokenException;
import com.tundra.response.AdminValidationResponse;

@Service
public class AdminSecurityServiceImpl extends AbstractSecurityService implements AdminSecurityService {

	private static final String INVALID_LOGIN = "Invalid login";

	private static final long EXPIRE_MILIS = TimeUnit.MINUTES.toMillis(10);

	@Autowired
	UserDAO userDAO;

	@Override
	public AdminValidationResponse validate(String token) {

		User user = doValidate(token);
		
		if (user == null) {
			throw new SecurityException(INVALID_LOGIN);
		}

		return createValidationResponse(user);
	}

	@Override
	public AdminValidationResponse login(String userName, String password) throws SecurityException {
		 // TODO: Placeholder. This needs to be thought out a bit. 
		
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			throw new SecurityException(INVALID_LOGIN);
		}
		
		String encryptedPassword = encode(password);
		List<User> users = userDAO.findByUserNameAndPassword(userName, encryptedPassword);
		if (users == null || users.isEmpty() || users.size() > 1) {
			throw new SecurityException(INVALID_LOGIN);
		}

		return createValidationResponse(users.get(0));
		
	}

	@Override
	public String getToken(User user) {
		return createSignedToken(encode(createTokenPayload(user)));
	}
	
	@Override
	public AdminValidationResponse renew(String token) {
		User user = doValidate(token);

		if (user == null) {
			throw new SecurityException(INVALID_LOGIN);
		}
		
		return createValidationResponse(user);
	}
	
	private User doValidate(String token) {
		
		// check the date... it's always in the second position
		User user = null;
		String id = null;
		
		try {
			token = getPayload(token);

			// decrypt it - token ends up as UUID^^DATE_TIME^^ID
			String source = decode(token);
			String[] sourceElements = StringUtils.split(source, DELIMITER);
			
			id = sourceElements[2];
			if (!isValid(sourceElements, EXPIRE_MILIS)) {
				throw new ExpiredTokenException("Expired token: " + token);
			}
		} catch (Exception e) {
			throw new InvalidTokenException("Invalid token: " + token);
		}
		
		// now make sure the user is valid
		if (id != null) {
			try {
		
				// TODO: finish this... needs to be thought out a bit more
				
				if (StringUtils.isNoneBlank(id)) {
					// sorry guys... hibernate bought into the optional paradigm
					Optional<User> o = userDAO.findById(Integer.parseInt(id));
					user = o.get();
				}
			} catch (Exception e) {
				// just set the user to null and fall through to an invalid login 
				user = null;
			}
		}
		
		return user;
	}	
	
	private String createTokenPayload(User user) {
		// remove nulls and add delimiters
		// just use the user id in the payload... dave sartory's idea
		String payload = null;
		if (user != null && user.getId() != null) {
			payload = UUID.randomUUID().toString() + DELIMITER + // just push the date over one place so we always know where it is 
					new SimpleDateFormat(DATETIME_FORMAT).format(new Date()) + DELIMITER + user.getId();  //TODO: change to timestamp
		}
		
		return payload;
		
	}
	
	private AdminValidationResponse createValidationResponse(User user) {
		return new AdminValidationResponse(getToken(user), user);
	}
	
}
