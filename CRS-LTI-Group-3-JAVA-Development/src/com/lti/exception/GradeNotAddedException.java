package com.lti.exception;

public class GradeNotAddedException extends Exception {
	
	private String message;

	public GradeNotAddedException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
}
