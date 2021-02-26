package com.tavant.samplerestapi.exception;

public class UserNotFoundException extends Exception{
	public UserNotFoundException(String message) {
		super(message);
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() ;
	}

}
