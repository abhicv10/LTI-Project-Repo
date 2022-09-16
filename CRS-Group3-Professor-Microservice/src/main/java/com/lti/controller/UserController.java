package com.lti.controller;

import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lti.bean.Login;
import com.lti.bean.Student;
import com.lti.exception.InvalidUserException;
import com.lti.service.UserInterfaceOperation;
import com.lti.service.UserService;
import com.lti.exception.UserAlreadyExistException;

/**
 * @author 10710133
 *
 */

@RestController
public class UserController {

	/**
	 * Method to verify login detals
	 * 
	 * @param login - login details to verify
	 * @return
	 * @throws InvalidUserException
	 */

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/login")
	public ResponseEntity<Object> verifyCredential(@RequestBody Login login) throws InvalidUserException {
		UserInterfaceOperation service = new UserService();
		boolean result = service.verifyCredential(login);

		if (!result) {
			throw new InvalidUserException("invalid user login");
		} else {
			return new ResponseEntity<>("user login successfull", HttpStatus.OK);
		}
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({ InvalidUserException.class })
	public ResponseEntity<Object> conflict(InvalidUserException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	}

	/**
	 * Method to register a new student
	 * 
	 * @param student - details of student to register
	 * @throws UserAlreadyExistException
	 */

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/register-student")
	public ResponseEntity<Object> registerStudent(@RequestBody Student student) throws UserAlreadyExistException {
		UserInterfaceOperation service = new UserService();
		boolean result = service.registerStudent(student);
		if (!result) {
			throw new UserAlreadyExistException("student already registered!");
		} else {
			return new ResponseEntity<>("student registration successfull!", HttpStatus.OK);
		}
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler({ UserAlreadyExistException.class })
	public ResponseEntity<Object> userAlreadyExistExceptionHandler(UserAlreadyExistException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Method to reset a users password
	 * 
	 * @param login       - user login details
	 * @param newPassword - newly created password
	 */

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, value = "/reset-password/{newPassword}")
	public ResponseEntity<Object> resetPassword(@RequestBody Login login,
			@PathVariable("newPassword") String newPassword) throws InvalidUserException {
		UserInterfaceOperation service = new UserService();

		if (service.resetPassword(login, newPassword)) {
			return new ResponseEntity<>("password reset successful!", HttpStatus.OK);
		} else {
			throw new InvalidUserException("invalid user login, password reset failed");
		}
	}
}
