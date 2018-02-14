package com.tundra.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tundra.dao.RegisteredDeviceDAO;
import com.tundra.entity.RegisteredDevice;
import com.tundra.exception.ExpiredTokenException;
import com.tundra.exception.InvalidTokenException;

@Service
public class SecurityServiceImpl extends AbstractSecurityService implements SecurityService {

	@Autowired
	RegisteredDeviceDAO registeredDeviceDAO;
	
//	private static final long EXPIRE_MILIS = TimeUnit.HOURS.toMillis(1);
	private static final long EXPIRE_MILIS = TimeUnit.SECONDS.toMillis(10);

	@Override
	public String getToken(String email) {
		
		if (StringUtils.isBlank(email)) {
			throw new SecurityException("Invalid authentication");
		}
		
		String firstName = "";
		String lastName = "";
		
		List<RegisteredDevice> devices = registeredDeviceDAO.findByEmail(email);
		
		// TODO: limit this to only registered devices
		// there should be only one of these
		if (devices == null || devices.isEmpty()) {
			throw new SecurityException("Invalid authentication");
		}
		
		RegisteredDevice device = devices.get(0);
		if (device != null) {
			firstName = device.getFirstName();
			lastName = device.getLastName();
		}
		
		return encode(createTokenPayload(firstName,lastName,email));
	}

	@Override
	public String validate(String token) throws SecurityException {
		try {
			
			String source = decode(token);
			String[] sourceElements = StringUtils.split(source, DELIMITER);
			
			if (!isValid(sourceElements, EXPIRE_MILIS)) {
				throw new ExpiredTokenException("Expired token: " + token);
			}
			
			String email = sourceElements[4];
			return getToken(email);
			
		} catch (Exception e) {
			throw new InvalidTokenException("Invalid token: " + token);
		}
	}

	@Override
	public RegisteredDevice register(String email, String firstName, String lastName, String deviceId, String platform) {
		List<RegisteredDevice> devices = registeredDeviceDAO.findByEmail(email);
		
		RegisteredDevice device = null;
		
		if (devices != null && !devices.isEmpty()) {
			device = devices.get(0);
		}

		if (device == null) {
			
			device = new RegisteredDevice();
		}

		device.setFirstName(firstName);
		device.setLastName(lastName);
		device.setPlatform(platform);
		device.setEmail(email);
		device.setDeviceId(deviceId);
		
		registeredDeviceDAO.save(device);
		
		return device;
	}

	private String createTokenPayload(String firstName, String lastName, String email) {
		// remove nulls and add delimiters
		// token ends up as UUID^^DATE_TIME^^FIRST_NAME^^LAST_NAME^^EMAIL
		String source = UUID.randomUUID().toString() + DELIMITER + // just push the date over one place so we always know where it is 
				new SimpleDateFormat(DATETIME_FORMAT).format(new Date()) + DELIMITER +
				StringUtils.defaultString(firstName) + DELIMITER + 
				StringUtils.defaultString(lastName) + DELIMITER + 
				StringUtils.defaultString(email) + DELIMITER;
		
		return source;
	}

	@Override
	public String getEmail(String token) {
		// decrypt it
		String source = decode(token);
		String[] sourceElements = StringUtils.split(source, DELIMITER);
		return sourceElements[4];
	}

}
