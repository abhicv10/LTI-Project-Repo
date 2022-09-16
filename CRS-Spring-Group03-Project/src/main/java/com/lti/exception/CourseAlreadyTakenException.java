/**
 * 
 */
package com.lti.exception;

/**
 * @author 10710133
 *
 */
public class CourseAlreadyTakenException extends Exception {
	private String message;

	public CourseAlreadyTakenException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
}
