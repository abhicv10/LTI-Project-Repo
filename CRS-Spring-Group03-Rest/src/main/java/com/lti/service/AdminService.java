package com.lti.service;

import java.util.ArrayList;

import com.lti.bean.Course;
import com.lti.bean.Grade;
import com.lti.bean.Professor;

import com.lti.dao.AdminDaoImplementation;
import com.lti.dao.ProfessorDaoImplementation;
import com.lti.dao.RegistrationDaoImplementation;
import com.lti.dao.StudentDaoImplementation;
import com.lti.dao.UserDaoImplementation;
import com.lti.exception.CourseFoundException;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.GradeNotAddedException;
import com.lti.exception.ProfessorNotFoundException;
import com.lti.exception.StudentNotFoundException;
import com.lti.exception.UserAlreadyExistException;

public class AdminService implements AdminInterfaceOperation {

	public boolean addProfessor(Professor professor) {

		boolean result = false;

		UserDaoImplementation userDao = new UserDaoImplementation();
		if (userDao.isUsernameAlreadyTaken(professor.getLogin().getUsername())) {
			return false;
		}

		ProfessorDaoImplementation profDao = new ProfessorDaoImplementation();
		profDao.addProfessor(professor);

		try {
			userDao.createNewUser(professor.getLogin());
			result = true;
		} catch (UserAlreadyExistException e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

	public boolean approveStudentRegistration(int studentID) {
		boolean result = false;

		StudentDaoImplementation stuDao = new StudentDaoImplementation();
		try {
			stuDao.approveStudent(studentID);
			result = true;
		} catch (StudentNotFoundException e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

	public void addCourse(Course course) throws CourseFoundException, ProfessorNotFoundException {
		AdminDaoImplementation adminDao = new AdminDaoImplementation();
		adminDao.addCourse(course);
		// try {
		// } catch (CourseFoundException e) {
		// System.out.println(e.getMessage());
		// } catch (ProfessorNotFoundException e) {
		// System.out.println(e.getMessage());
		// }
	}

	public boolean removeCourse(int courseId) {
		boolean result = false;

		AdminDaoImplementation adminDao = new AdminDaoImplementation();
		try {
			adminDao.removeCourse(courseId);
			result = true;
		} catch (CourseNotFoundException e) {

		}
		return result;
	}

	public ArrayList<Grade> generateReportCard(int studentID) throws GradeNotAddedException {
		AdminDaoImplementation adminDao = new AdminDaoImplementation();
		ArrayList<Grade> grades = adminDao.getGrades(studentID);
		;

		// try {
		// } catch(GradeNotAddedException e) {
		// System.out.println(e.getMessage());
		// }

		return grades;
	}

	public ArrayList<Course> getAllCourseList() {
		RegistrationDaoImplementation regDao = new RegistrationDaoImplementation();
		return regDao.getCourseList();
	}
}
