package com.tundra.exception;

public class DecryptionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DecryptionException() {
		super();
	}

	public DecryptionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DecryptionException(String message, Throwable cause) {
		super(message, cause);
	}

	public DecryptionException(String message) {
		super(message);
	}

	public DecryptionException(Throwable cause) {
		super(cause);
	}

}
