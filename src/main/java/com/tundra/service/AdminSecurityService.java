package com.tundra.service;

/**
 * Security service that manages tokens and such for a user
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
	
}
