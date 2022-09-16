/**
 * 
 */
package com.lti.service;

import org.jvnet.hk2.annotations.Service;

import com.lti.bean.Login;
import com.lti.bean.Student;

/**
 * @author 10710133
 *
 */

public interface UserInterfaceOperation {
	/**
	 * Method to verify user credential
	 * 
	 * @param login - login details
	 * @return returns true if login details are valid
	 */
	public boolean verifyCredential(Login login);

	/**
	 * Method to register a new student
	 * 
	 * @param student - student to be added
	 */
	public boolean registerStudent(Student student);

	/**
	 * Method to reset or update student password
	 * 
	 * @param login        - login details of the user
	 * @param newPassword- The new password to be added
	 */
	public boolean resetPassword(Login login, String newPassword);

}
