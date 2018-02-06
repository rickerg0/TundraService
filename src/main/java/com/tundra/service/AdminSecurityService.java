package com.tundra.service;

import com.tundra.entity.User;

/**
 * Security service that manages tokens and such for a user
 * 
 * Token contains the following information:
 * 
 *     user id, date/time token was issued
 * 
 * and is in the following format:
 * 
 *     RANDOM UUID^^DATE_TIME^^ID
 * 
 * @author G
 *
 */
public interface AdminSecurityService {

	/**
	 * Gets a token for a user
	 * 
	 * @param user
	 * @return
	 */
	String getToken(User user);
	
	/**
	 * Checks a token for validity and throws an exception if invalid
	 * 
	 * @param token
	 * @return user
	 */
	User validate(String token);
	
	/**
	 * Checks a token for validity and returns a renewed one, throws an exception if invalid
	 * 
	 * @param token
	 * @return
	 */
	String renew(String token);
	
	/**
	 * Checks creds and returns token if valid
	 * @param userName
	 * @param password
	 * @return
	 */
	String login(String userName, String password) throws SecurityException;

	/**
	 * Encodes a string.
	 * @param payload
	 * @return
	 */
	String encode(String payload);
	
}
