package com.lti.service;

import com.lti.bean.Login;
import com.lti.bean.Student;
import com.lti.dao.StudentDaoImplementation;
import com.lti.dao.UserDaoImplementation;
import com.lti.exception.InvalidUserException;
import com.lti.exception.UserAlreadyExistException;

/**
 * @author 10710133
 *
 */

public class UserService implements UserInterfaceOperation {

	public boolean verifyCredential(Login login) {
		UserDaoImplementation userDao = new UserDaoImplementation();

		boolean result = false;

		try {
			result = userDao.validateUser(login.getUsername(), login.getPassword(), login.getRole());
		} catch (InvalidUserException e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

	public boolean registerStudent(Student student) {

		boolean result = false;

		UserDaoImplementation userDao = new UserDaoImplementation();

		if (userDao.isUsernameAlreadyTaken(student.getLogin().getUsername())) {
			return false;
		}

		StudentDaoImplementation studentDao = new StudentDaoImplementation();
		studentDao.addStudent(student);

		try {
			userDao.createNewUser(student.getLogin());
			result = true;
		} catch (UserAlreadyExistException e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

	public boolean resetPassword(Login login, String newPassword) {

		boolean result = this.verifyCredential(login);

		if (result) {
			UserDaoImplementation userDao = new UserDaoImplementation();
			userDao.updatePassword(login.getUsername(), newPassword);
		}

		return result;
	}
}
