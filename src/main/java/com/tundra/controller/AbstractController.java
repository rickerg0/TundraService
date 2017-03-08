package com.tundra.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tundra.service.SecurityService;

@SuppressWarnings("serial")
public abstract class AbstractController implements Serializable {
	
	static final String ERROR_PREFIX = "Whoops : ";
	static final String HEADER_SECURITY_TOKEN = "X-Token";
	
	@Autowired
	private SecurityService securityService;

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	boolean validateRequest(String token) {
		try {
			return securityService.isValid(token);
		} catch (Exception e) {
			return false;
		}
	}
	
	HttpStatus getStatusCode(Throwable t) {
		HttpStatus code = HttpStatus.INTERNAL_SERVER_ERROR;
		if (t instanceof SecurityException) {
			code = HttpStatus.FORBIDDEN;
		}
		return code;
	}
	
	ResponseEntity<?> getErrorResponseEntity(Throwable t) {
		return new ResponseEntity<ErrorResonse>(new ErrorResonse(ERROR_PREFIX + t.getMessage()) ,getStatusCode(t));
	}
	
	public class ErrorResonse {

		private String errorMessage;

		public ErrorResonse(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		
	}
}
