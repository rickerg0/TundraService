package com.tundra.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.tundra.exception.DecryptionException;
import com.tundra.exception.EncryptionException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class SecurityUtil {

	private static final byte[] KEY = new byte[16];
	private static final SecureRandom random = new SecureRandom();
	private static final Key AES_KEY;
	private static final Key TOKEN_KEY = MacProvider.generateKey();
	
	static {
		random.nextBytes(KEY);
		AES_KEY = new SecretKeySpec(KEY, "AES");
	}
	
	public static String encode(String payload) {
		
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
	
	public static  String decode(String payload) {
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
	
	public static final String createSignedToken(String payload) {
		return Jwts.builder().setSubject(payload).signWith(SignatureAlgorithm.HS512, TOKEN_KEY).compact();
	}
	
	public static final String getPayload(String signedToken) {
		// this should blow an exception if the key is invalid
		return Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(signedToken).getBody().getSubject();
	}
	
}
