/**
 * 
 */
package com.lti.service;

import com.lti.bean.Login;
import com.lti.bean.Student;

/**
 * @author 10710133
 *
 */

public interface UserInterfaceOperation {
	/**
	 * Method to verify user credential
	 * @param login- login details
	 * @return: returns true if login details are valid
	 */
	public boolean verifyCredential(Login login);
	
	/**
	 * Method to register a new student
	 * @param student- student to be added
	 * @param login- Login details
	 */
	public void registerStudent(Student student, Login login);
	
	/**
	 * Method to reset or update student password
	 * @param username- The username of the user
	 * @param newPassword- The new password to be added 
	 */
	public void resetPassword(String username, String newPassword);
	
}
