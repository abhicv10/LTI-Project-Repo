/**
 * 
 */
package com.lti.dao;

import com.lti.bean.Course;
import java.util.ArrayList;
import com.lti.exception.*;

/**
 * @author 10710133
 *
 */
public interface RegistrationDao {

	/**
	 * Method to Gets the list of all courses
	 * 
	 * @return list of all courses
	 */
	public ArrayList<Course> getCourseList();

	/**
	 * Method to Registers a courese for a student
	 * 
	 * @param studentID - id of student to register course for
	 * @param courseID  - id of the course to register
	 * @throws StudentNotFoundException
	 * @throws CourseNotFoundException
	 * @throws CourseLimitExceedException
	 * @throws CourseAlreadyTakenException
	 */
	public void registerCourse(int studentID, int courseID) throws StudentNotFoundException, CourseNotFoundException,
			CourseLimitExceedException, CourseAlreadyTakenException;

	/**
	 * Method to Deregister a course for a student
	 * 
	 * @param studentID - id of student to deregister course for
	 * @param courseID  - id of the course to deregister
	 * @throws StudentNotFoundException
	 * @throws CourseNotFoundException
	 */
	public void deRegisterCourse(int studentID, int courseID) throws StudentNotFoundException, CourseNotFoundException;

	/**
	 * Method to Gets the list of courses taken by a student
	 * 
	 * @param studentID - id of student to get list of courses
	 * @return list of courses
	 * @throws StudentNotFoundException
	 */
	public ArrayList<Course> getStudentCourseList(int studentID) throws StudentNotFoundException;

}
