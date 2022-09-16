package com.lti.service;

import java.util.ArrayList;

import com.lti.bean.Course;
import com.lti.bean.Grade;
import com.lti.bean.Payment;

import com.lti.dao.AdminDaoImplementation;
import com.lti.dao.RegistrationDaoImplementation;
import com.lti.dao.StudentDaoImplementation;
import com.lti.exception.CourseAlreadyTakenException;
import com.lti.exception.CourseLimitExceedException;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.GradeNotAddedException;
import com.lti.exception.PaymentAlreadyDoneException;
import com.lti.exception.StudentNotFoundException;

/**
 * @author 10710133
 *
 */

public class StudentService implements StudentInterfaceOperation {

	public void addCourse(int studentID, int courseID) throws StudentNotFoundException, CourseNotFoundException,
			CourseLimitExceedException, CourseAlreadyTakenException {
		RegistrationDaoImplementation regDao = new RegistrationDaoImplementation();
		regDao.registerCourse(studentID, courseID);
	}

	public void dropCourse(int studentID, int courseID) throws StudentNotFoundException, CourseNotFoundException {
		RegistrationDaoImplementation regDao = new RegistrationDaoImplementation();
		regDao.deRegisterCourse(studentID, courseID);
	}

	public ArrayList<Course> viewEnrolledCourses(int studentID) throws StudentNotFoundException {
		RegistrationDaoImplementation regDao = new RegistrationDaoImplementation();
		ArrayList<Course> courseList = regDao.getStudentCourseList(studentID);
		return courseList;
	}

	public ArrayList<Grade> viewGrades(int studentID) throws GradeNotAddedException {
		AdminDaoImplementation adminDoa = new AdminDaoImplementation();
		ArrayList<Grade> grades = adminDoa.getGrades(studentID);
		return grades;
	}

	public void payFee(Payment payment) throws PaymentAlreadyDoneException {
		StudentDaoImplementation stuDao = new StudentDaoImplementation();
		stuDao.payFee(payment);
	}
}
