package com.example.order_microservice.exceptions;

public class OrderItemNotFoundException extends RuntimeException {
	
	public OrderItemNotFoundException(String msg) {
		super(msg);
	}

}
