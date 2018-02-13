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
	 * Gets an email for a token
	 * 
	 * @param token
	 * @return
	 */
	String getEmail(String token);
	
	/**
	 * Checks a token for validity and throws an exception if invalid
	 * 
	 * @param token
	 * @return a new token with an updated date/time
	 */
	String validate(String token);
	
	/**
	 * Register a device
	 * 
	 * @param email
	 * @param firstName
	 * @param lastName
	 * @param deviceId
	 * @param platform
	 * @return
	 */
	RegisteredDevice register(String email, String firstName, String lastName, String deviceId, String platform);
	
}
