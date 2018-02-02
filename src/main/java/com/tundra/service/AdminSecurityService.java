package com.tundra.service;

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
	 * Checks a token for validity and throws an exception if invalid
	 * 
	 * @param token
	 * @return
	 */
	void validate(String token);
	
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
