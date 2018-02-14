package com.tundra.controller.exceptionhandlers;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public abstract class AbstractExceptionHandler extends ResponseEntityExceptionHandler {

	static final String ERROR_PREFIX = "Whoops : ";
	
	public class ErrorResponse {

		private String errorMessage;

		public ErrorResponse(String errorMessage) {
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
