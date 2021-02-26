package com.tavant.samplerestapi.exception;

public class WrongUserCredentialException extends Exception{
	public WrongUserCredentialException(String message) {
		super(message);
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() ;
	}

}
