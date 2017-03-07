package com.tundra.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.tundra.util.SecurityUtil;

@Service
public class SecurityServiceImpl implements SecurityService {

	private static final String DELIMITER = "^^";
	private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	private static final long EXPIRE_MILIS = 10000;

	@Override
	public String getToken(String firstName, String lastName, String email) throws Exception {
		
		// remove nulls and add delimiters
		String source = UUID.randomUUID().toString() + DELIMITER + // just push the date over one place so we always know where it is 
				new SimpleDateFormat(DATETIME_FORMAT).format(new Date()) + DELIMITER +
				StringUtils.defaultString(firstName) + DELIMITER + 
				StringUtils.defaultString(lastName) + DELIMITER + 
				StringUtils.defaultString(email) + DELIMITER;
		
		return SecurityUtil.encode(source);
	}

	@Override
	public boolean isValid(String token) throws Exception {
		// decrypt it
		String[] sourceElements = SecurityUtil.decode(token).split(DELIMITER);
		Date tokenDate = new SimpleDateFormat(DATETIME_FORMAT).parse(sourceElements[1]);
		
		return (new Date().getTime() - tokenDate.getTime() < EXPIRE_MILIS);
	}

}