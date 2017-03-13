package com.tundra.service;

import com.tundra.entity.RegisteredDevice;

/**
 * Security service that manages tokens and such
 * 
 * Token contains the following information:
 * 
 *     user first name, user last name, user email address, date token was issued
 * 
 * and is in the following format:
 * 
 *     RANDOM UUID^^DATE^^FIRST_NAME^^LAST_NAME^^EMAIL
 * 
 * @author G
 *
 */
public interface SecurityService {

	String getToken(String email) throws Exception;
	
	boolean isValid(String token) throws Exception;
	
	void validate(String token) throws SecurityException;
	
	RegisteredDevice register(String email, String firstName, String lastName, String deviceId, String platform);
	
}
