package com.demo.doman.car.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomeExceptionHandling {
	
	
	@ExceptionHandler(value=NullPointerException.class)
	public ResponseEntity<Object> handleNullPointerException (NullPointerException ex){
		
		 return  new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
