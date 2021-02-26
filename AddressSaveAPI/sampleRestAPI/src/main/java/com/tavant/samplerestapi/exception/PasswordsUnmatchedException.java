package com.tavant.samplerestapi.exception;

public class PasswordsUnmatchedException extends Exception{
	public PasswordsUnmatchedException(String message) {
		super(message);
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() ;
	}

}
