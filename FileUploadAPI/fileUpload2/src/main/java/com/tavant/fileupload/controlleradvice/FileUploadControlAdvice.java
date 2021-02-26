package com.tavant.fileupload.controlleradvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.tavant.fileupload.errorresponse.ErrorResponse;
import com.tavant.fileupload.exception.FileNotFoundException;


@ControllerAdvice
public class FileUploadControlAdvice {
	 // To handle the specific error response 
	@ExceptionHandler(FileNotFoundException.class)
	
	public final ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(FileNotFoundException e, WebRequest req)
	{
		List<String> details= new ArrayList<String>();
		details.add(e.getLocalizedMessage());
		
		// Customised output for exceptions 
		ErrorResponse errorResponse = new ErrorResponse("INCORRECT_RESPONSE",details);
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	
	
	
	
}
