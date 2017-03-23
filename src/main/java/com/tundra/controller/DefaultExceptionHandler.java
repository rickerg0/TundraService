package com.tundra.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class DefaultExceptionHandler extends AbstractExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<?> handleConflict(Exception ex, WebRequest request) {
		return new ResponseEntity<ErrorResonse>(new ErrorResonse(ERROR_PREFIX + ex.getMessage()) ,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}	
