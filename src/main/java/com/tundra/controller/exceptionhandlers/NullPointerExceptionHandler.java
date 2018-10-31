package com.tundra.controller.exceptionhandlers;

import org.apache.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * Handles the NullPointerException
 *
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class NullPointerExceptionHandler extends AbstractExceptionHandler {
 
	private final static Logger logger = Logger.getLogger(NullPointerExceptionHandler.class);
	
    @ExceptionHandler(value = { NullPointerException.class })
    protected ResponseEntity<?> handleConflict(Exception ex, WebRequest request) {
    	logger.error("Null error", ex);
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ERROR_PREFIX + "Required information was not provided or available") ,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}	
