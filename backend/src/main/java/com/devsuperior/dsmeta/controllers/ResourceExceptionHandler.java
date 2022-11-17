package com.devsuperior.dsmeta.controllers;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest; // version 2.XX

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.dsmeta.controllers.exceptions.StandardError;
import com.devsuperior.dsmeta.services.exceptions.ResourceNotFoundException;


// this annotation intercepts exceptions to be handled by the class below
@ControllerAdvice
public class ResourceExceptionHandler {

	// Annotation for handling exceptions in specific handler classes and/orhandler methods. 
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Resource not found.";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	

    //  handle bad JSON formats in PUT/POST messages
 	@ExceptionHandler(HttpMessageNotReadableException.class)
 	public ResponseEntity<StandardError> jsonError(RuntimeException e, HttpServletRequest request){
 		String error = "JSON error.";
 	HttpStatus status = HttpStatus.BAD_REQUEST;
 		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),request.getRequestURI());
 		return ResponseEntity.status(status).body(err);
 	}	
 	
	
}
