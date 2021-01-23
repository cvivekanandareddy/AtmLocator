package com.vivekananda.atm.exception;

public class ParseException extends RuntimeException {

	private static final long serialVersionUID = 352999412956558794L;

	public ParseException() {
		super();
	}
	
	public ParseException(String message) {
		super(message);
	}
	
	public ParseException(Throwable cause) {
		super(cause);
	}

	public ParseException(String message, Throwable cause) {
		super(message, cause);
	}
}
