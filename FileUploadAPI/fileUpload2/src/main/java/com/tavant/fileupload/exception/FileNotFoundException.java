package com.tavant.fileupload.exception;

public class FileNotFoundException extends Exception {

	
	public FileNotFoundException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
	
	@Override
	public String toString() {
		return super.toString()+super.getMessage();
	}
	
}
