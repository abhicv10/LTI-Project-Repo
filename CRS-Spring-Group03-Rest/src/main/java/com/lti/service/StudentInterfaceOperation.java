/**
 * 
 */
package com.lti.service;

import java.util.ArrayList;

import com.lti.bean.Course;
import com.lti.bean.Grade;
import com.lti.bean.Payment;
import com.lti.exception.*;

/**
 * @author 10710133
 *
 */

public interface StudentInterfaceOperation {

	/**
	 * Method to Adds a course to student course list
	 * 
	 * @param studentID - Get id of student
	 * @param courseID  - Get id of course
	 * @throws StudentNotFoundException
	 * @throws CourseNotFoundException
	 * @throws CourseLimitExceedException
	 * @throws CourseAlreadyTakenException
	 */
	public void addCourse(int studentID, int courseID) throws StudentNotFoundException, CourseNotFoundException,
			CourseLimitExceedException, CourseAlreadyTakenException;

	/**
	 * Method to Drops a course to student course list
	 * 
	 * @param studentID - Get id of student
	 * @param courseID  - Get id of course
	 * @throws StudentNotFoundException
	 * @throws CourseNotFoundException
	 */
	public void dropCourse(int studentID, int courseID) throws StudentNotFoundException, CourseNotFoundException;

	/**
	 * Method to Get list of courses enrolled by the student
	 * 
	 * @param studentID - Get id of student
	 * @return list of enrolled courses
	 * @throws StudentNotFoundException
	 */
	public ArrayList<Course> viewEnrolledCourses(int studentID) throws StudentNotFoundException;

	/**
	 * Method to Get list of grades for a student
	 * 
	 * @param studentID - Get id of student
	 * @return list of grades
	 * @throws GradeNotAddedException
	 * 
	 */
	public ArrayList<Grade> viewGrades(int studentID) throws GradeNotAddedException;

	/**
	 * Method to Pays fee for a student
	 * 
	 * @param payment - Get payment bean
	 * @throws GradeNotAddedException
	 */
	public void payFee(Payment payment) throws PaymentAlreadyDoneException;
}
