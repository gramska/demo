package com.ago.demo.exception;

public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = 940160373686206456L;
	private static final String DESCRIPTION = "Bad Request Exception (400)";

	public BadRequestException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}	
}
