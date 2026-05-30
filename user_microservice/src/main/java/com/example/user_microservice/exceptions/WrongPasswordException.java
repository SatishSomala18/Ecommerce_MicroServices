package com.example.user_microservice.exceptions;

public class WrongPasswordException extends RuntimeException{

	
	public WrongPasswordException(String msg) {
		super(msg);
	}
}
