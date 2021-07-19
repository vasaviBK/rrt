package com.capgemini.globalexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capgemini.exceptions.NoSuchStudentException;

@ControllerAdvice
public class GlobalExcception {
	//Handling the exception
	@ExceptionHandler(value = NoSuchStudentException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Student Not Found.")
	public void handleException(NoSuchStudentException e) {
		
	}
}
