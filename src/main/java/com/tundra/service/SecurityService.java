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

	/**
	 * Gets a token for a registered device
	 * 
	 * @param email
	 * @return
	 */
	String getToken(String email);
	
	/**
	 * Checks a token for validity
	 * 
	 * @param token
	 * @return
	 */
	//boolean isValid(String token);
	
	/**
	 * Checks a token for validity and throws an exception if invalid
	 * 
	 * @param token
	 * @return
	 */
	void validate(String token);
	
	/**
	 * Checks a token for validity and throws an exception if invalid
	 * 
	 * @param token
	 * @return
	 */
	void validateAdmin(String token);
	
	/**
	 * Register a device
	 * 
	 * @param email
	 * @param firstName
	 * @param lastName
	 * @param deviceId
	 * @param platform
	 * @param userName
	 * @return
	 */
	RegisteredDevice register(String email, String firstName, String lastName, String deviceId, String platform, String userName);
	
	/**
	 * Checks creds and returns token if valid
	 * @param userName
	 * @param password
	 * @return
	 */
	String adminLogin(String userName, String password) throws SecurityException;
	
}
