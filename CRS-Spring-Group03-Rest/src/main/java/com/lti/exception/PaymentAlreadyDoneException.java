/**
 * 
 */
package com.lti.exception;

/**
 * @author 10710133
 *
 */
public class PaymentAlreadyDoneException extends Exception {
	private String message;

	public PaymentAlreadyDoneException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
}
