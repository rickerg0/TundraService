package com.tundra.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tundra.dao.RegisteredDeviceDAO;
import com.tundra.entity.RegisteredDevice;
import com.tundra.exception.ExpiredTokenException;
import com.tundra.exception.InvalidTokenException;
import com.tundra.util.SecurityUtil;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	RegisteredDeviceDAO registeredDeviceDAO;
	
//	private static final long EXPIRE_MILIS = TimeUnit.HOURS.toMillis(1);
	private static final long EXPIRE_MILIS = TimeUnit.SECONDS.toMillis(10);

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
		
		return SecurityUtil.encode(SecurityUtil.createToken(firstName,lastName,email));
	}

	@Override
	public void validate(String token) throws SecurityException {
		try {
			if (!SecurityUtil.isValid(token, EXPIRE_MILIS)) {
				throw new ExpiredTokenException("Expired token: " + token);
			}
		} catch (Exception e) {
			throw new InvalidTokenException("Invalid token: " + token);
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

}
