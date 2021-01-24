package com.vivekananda.atm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionalMapper extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ParseException.class)
	public ResponseEntity<String> handleCustomParseException() {
		return new ResponseEntity<>("Facing issue with another server", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleNullPointerException() {
		return new ResponseEntity<>("We are facing data issue", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
