package com.ago.demo.exception;

public class NotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1469540083030125890L;
	private static final String DESCRIPTION = "Not Found Exception (404)";

	public NotFoundException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}
}
