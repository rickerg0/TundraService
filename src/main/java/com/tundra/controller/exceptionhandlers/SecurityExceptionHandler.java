package com.tundra.controller.exceptionhandlers;

import org.apache.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityExceptionHandler extends AbstractExceptionHandler {
 
	private final static Logger logger = Logger.getLogger(DefaultExceptionHandler.class);
	
    @ExceptionHandler(value = { SecurityException.class })
    protected ResponseEntity<?> handleConflict(Exception ex, WebRequest request) {
    	logger.error("Security error", ex);
		return new ResponseEntity<ErrorResonse>(new ErrorResonse(ERROR_PREFIX + ex.getMessage()) ,HttpStatus.FORBIDDEN);
    }
}	
