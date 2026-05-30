package com.example.order_microservice.exceptions;

public class OrderNotFoundException extends RuntimeException {

	
	public OrderNotFoundException(String msg) {
		super(msg);
	}
}
