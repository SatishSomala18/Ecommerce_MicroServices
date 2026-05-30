package com.example.user_microservice.exceptions;

public class PaymentNotFoundException extends RuntimeException{

	
	public PaymentNotFoundException(String msg) {
		super(msg);
	}
}
