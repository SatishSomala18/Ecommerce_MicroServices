package com.example.order_microservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExcepionHandler {
        
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handler(Exception ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	@ExceptionHandler(OrderItemNotFoundException.class)
	public ResponseEntity<String> handler(OrderItemNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<String> handler(OrderNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
}
