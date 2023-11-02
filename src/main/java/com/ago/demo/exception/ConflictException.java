package com.ago.demo.exception;

public class ConflictException extends RuntimeException{

	private static final long serialVersionUID = 5702248899070129931L;
	private static final String DESCRIPTION = "Conflict Exception (409)";

	public ConflictException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}
}
