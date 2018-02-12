package com.tundra.exception;

public class AuditException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuditException() {
		super();
	}

	public AuditException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AuditException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuditException(String message) {
		super(message);
	}

	public AuditException(Throwable cause) {
		super(cause);
	}

}
