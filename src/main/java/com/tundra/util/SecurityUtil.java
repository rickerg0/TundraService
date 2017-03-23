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

public class SecurityUtil {

	private static final byte[] KEY = new byte[16];
	private static final SecureRandom random = new SecureRandom();
	private static final Key AES_KEY;
	
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
}
