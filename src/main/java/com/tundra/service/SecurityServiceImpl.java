package com.tundra.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tundra.dao.RegisteredDeviceDAO;
import com.tundra.dao.UserDAO;
import com.tundra.entity.RegisteredDevice;
import com.tundra.entity.User;
import com.tundra.exception.ExpiredTokenException;
import com.tundra.exception.InvalidTokenException;
import com.tundra.util.SecurityUtil;

@Service
public class SecurityServiceImpl implements SecurityService {

	private static final String INVALID_LOGIN = "Invalid login";

	@Autowired
	RegisteredDeviceDAO registeredDeviceDAO;
	
	@Autowired
	UserDAO userDAO;
	
	private static final String DELIMITER = "^^";
	private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
//	private static final long EXPIRE_MILIS = TimeUnit.HOURS.toMillis(1);
	private static final long EXPIRE_MILIS = TimeUnit.SECONDS.toMillis(10);
	private static final long ADMIN_EXPIRE_MILIS = TimeUnit.MINUTES.toMillis(10);

	@Override
	public String getToken(String email) {
		
		String firstName = "";
		String lastName = "";
		
		List<RegisteredDevice> devices = registeredDeviceDAO.findByEmail(email);
		
		// TODO: limit this to only registered devices
		// there should be only one of these
		if (devices != null && !devices.isEmpty()) {
			RegisteredDevice device = devices.get(0);
			if (device != null) {
				firstName = device.getFirstName();
				lastName = device.getLastName();
			}
		}
		
		return SecurityUtil.encode(createToken(firstName,lastName,email));
	}

	@Override
	public void validate(String token) throws SecurityException {
		try {
			if (!isValid(token, EXPIRE_MILIS)) {
				throw new ExpiredTokenException("Expired token: " + token);
			}
		} catch (Exception e) {
			throw new InvalidTokenException("Invalid token: " + token);
		}
	}

	@Override
	public void validateAdmin(String token) throws SecurityException {
		// check the date... it's always in the first position
		try {
			if (!isValid(token, ADMIN_EXPIRE_MILIS)) {
				throw new ExpiredTokenException("Expired token: " + token);
			}
		} catch (Exception e) {
			throw new InvalidTokenException("Invalid token: " + token);
		}
		
		// now make sure the user is valid
		// decrypt it - token ends up as UUID^^DATE_TIME^^FIRST_NAME^^LAST_NAME^^EMAIL^^USER_NAME

		String source = SecurityUtil.decode(token);
		String[] sourceElements = StringUtils.split(source, DELIMITER);

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
	public RegisteredDevice register(String email, String firstName, String lastName, String deviceId, String platform, String userName) {
		List<RegisteredDevice> devices = registeredDeviceDAO.findByEmail(email);
		
		RegisteredDevice device = null;
		
		if (devices != null && !devices.isEmpty()) {
			device = devices.get(0);
		}

		if (device == null) {
			
			device = new RegisteredDevice();
			device.setCreated(new Date());
			device.setCreatedUser(userName);
		}

		device.setFirstName(firstName);
		device.setLastName(lastName);
		device.setPlatform(platform);
		device.setEmail(email);
		device.setDeviceId(deviceId);
		device.setUpdated(new Date());
		device.setUpdatedUser(userName);
		
		registeredDeviceDAO.save(device);
		
		return device;
	}

	@Override
	public String adminLogin(String userName, String password) throws SecurityException {
		 // TODO: Placeholder. This needs to be thought out a bit. 
		
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			throw new SecurityException(INVALID_LOGIN);
		}
		
		String encryptedPassword = SecurityUtil.encode(password);
		List<User> users = userDAO.findByUserNameAndPassword(userName, encryptedPassword);
		if (users == null || users.isEmpty() || users.size() > 1) {
			throw new SecurityException(INVALID_LOGIN);
		}
		
		return SecurityUtil.encode(createAdminToken(users.get(0)));
		
	}
	
	private String createAdminToken(User user) {
		// remove nulls and add delimiters
		// token ends up as UUID^^DATE_TIME^^FIRST_NAME^^LAST_NAME^^EMAIL^^USER_NAME
		String source = UUID.randomUUID().toString() + DELIMITER + // just push the date over one place so we always know where it is 
				new SimpleDateFormat(DATETIME_FORMAT).format(new Date()) + DELIMITER +
				StringUtils.defaultString(user.getFirstName()) + DELIMITER + 
				StringUtils.defaultString(user.getLastName()) + DELIMITER + 
				StringUtils.defaultString(user.getEmail()) + DELIMITER + 
				StringUtils.defaultString(user.getUserName()) ;
		
		return source;
		
	}
	
	private String createToken(String firstName, String lastName, String email) {
		// remove nulls and add delimiters
		// token ends up as UUID^^DATE_TIME^^FIRST_NAME^^LAST_NAME^^EMAIL
		String source = UUID.randomUUID().toString() + DELIMITER + // just push the date over one place so we always know where it is 
				new SimpleDateFormat(DATETIME_FORMAT).format(new Date()) + DELIMITER +
				StringUtils.defaultString(firstName) + DELIMITER + 
				StringUtils.defaultString(lastName) + DELIMITER + 
				StringUtils.defaultString(email) + DELIMITER;
		
		return source;
	}

	private boolean isValid(String token, long expireMillis) {
		// decrypt it
		String source = SecurityUtil.decode(token);
		String[] sourceElements = StringUtils.split(source, DELIMITER);
		Date tokenDate;
		try {
			tokenDate = new SimpleDateFormat(DATETIME_FORMAT).parse(sourceElements[1]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		
		return ((tokenDate.getTime() + expireMillis) > new Date().getTime());
	}
	
}
