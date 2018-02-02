package com.tundra.service;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;

import com.tundra.entity.User;
import com.tundra.exception.DecryptionException;
import com.tundra.exception.EncryptionException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class AbstractSecurityService {
	
	static final String DELIMITER = "^^";
	private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final byte[] KEY = new byte[16];
	private static final SecureRandom random = new SecureRandom();
	private static final Key AES_KEY;
	private static final Key TOKEN_KEY = MacProvider.generateKey();
	
	static {
		random.nextBytes(KEY);
		AES_KEY = new SecretKeySpec(KEY, "AES");
	}
	
	public String encode(String payload) {
		
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, AES_KEY);
			
			return Base64.getEncoder().encodeToString(cipher.doFinal(payload.getBytes()));
			
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | 
				IllegalBlockSizeException | InvalidKeyException |
				BadPaddingException e) {
			throw new EncryptionException("Encryption error: " + e.getMessage());
		}
	}
	
	public String decode(String payload) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, AES_KEY);
			return new String(cipher.doFinal(Base64.getDecoder().decode(payload)));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | 
				IllegalBlockSizeException | InvalidKeyException |
				BadPaddingException e) {
			
			throw new DecryptionException("Decryption error: " + e.getMessage());
		}
	}
	
	public final String createSignedToken(String payload) {
		return Jwts.builder().setSubject(payload).signWith(SignatureAlgorithm.HS512, TOKEN_KEY).compact();
	}
	
	public final String getPayload(String signedToken) {
		// this should blow an exception if the key is invalid
		return Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(signedToken).getBody().getSubject();
	}
	
	public String createAdminToken(User user) {
		// remove nulls and add delimiters
		// payload ends up as UUID^^DATE_TIME^^FIRST_NAME^^LAST_NAME^^EMAIL^^USER_NAME
		String payload = UUID.randomUUID().toString() + DELIMITER + // just push the date over one place so we always know where it is 
				new SimpleDateFormat(DATETIME_FORMAT).format(new Date()) + DELIMITER +
				StringUtils.defaultString(user.getFirstName()) + DELIMITER + 
				StringUtils.defaultString(user.getLastName()) + DELIMITER + 
				StringUtils.defaultString(user.getEmail()) + DELIMITER + 
				StringUtils.defaultString(user.getUserName()) ;
		
		return payload;
		
	}
	
	public String createToken(String firstName, String lastName, String email) {
		// remove nulls and add delimiters
		// token ends up as UUID^^DATE_TIME^^FIRST_NAME^^LAST_NAME^^EMAIL
		String source = UUID.randomUUID().toString() + DELIMITER + // just push the date over one place so we always know where it is 
				new SimpleDateFormat(DATETIME_FORMAT).format(new Date()) + DELIMITER +
				StringUtils.defaultString(firstName) + DELIMITER + 
				StringUtils.defaultString(lastName) + DELIMITER + 
				StringUtils.defaultString(email) + DELIMITER;
		
		return source;
	}

	public boolean isValid(String token, long expireMillis) {
		// decrypt it
		String source = decode(token);
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
