package com.tavant.samplerestapi.controlleradvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.tavant.samplerestapi.errorresponse.ErrorResponse;
import com.tavant.samplerestapi.exception.PasswordsUnmatchedException;
import com.tavant.samplerestapi.exception.UserNotFoundException;
import com.tavant.samplerestapi.exception.WrongUserCredentialException;


@ControllerAdvice
public class RestControllerAdvice {
	
	@ExceptionHandler(PasswordsUnmatchedException.class)
	
	public final ResponseEntity<ErrorResponse> handlePasswordsUnmatchedException(PasswordsUnmatchedException e , WebRequest request){
		List<String> details = new ArrayList<String>();
		details.add(e.getLocalizedMessage());
		ErrorResponse errorResponse = new ErrorResponse("INCORRECT_REQUEST",details);
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(UserNotFoundException.class)
	
	public final ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e , WebRequest request){
		List<String> details = new ArrayList<String>();
		details.add(e.getLocalizedMessage());
		ErrorResponse errorResponse = new ErrorResponse("INCORRECT_REQUEST",details);
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}
@ExceptionHandler(WrongUserCredentialException.class)
	
	public final ResponseEntity<ErrorResponse> handleWrongUserCredentialException(WrongUserCredentialException e , WebRequest request){
		List<String> details = new ArrayList<String>();
		details.add(e.getLocalizedMessage());
		ErrorResponse errorResponse = new ErrorResponse("INCORRECT_REQUEST",details);
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}



}
