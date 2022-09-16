/**
 * 
 */
package com.lti.service;

import java.util.ArrayList;

import com.lti.bean.Course;
import com.lti.bean.Grade;
import com.lti.bean.Login;
import com.lti.bean.Professor;

import com.lti.exception.*;

/**
 * @author 10710133
 *
 */

public interface AdminInterfaceOperation {

	/**
	 * Method to add a professor
	 * 
	 * @param professor - professsor to add
	 * @return returns true if adding professor was successful or else false
	 */
	public boolean addProfessor(Professor professor);

	/**
	 * Method to approve registration of a student
	 * 
	 * @param studentID - id of the student to approve
	 * @return returns true if student approval successful or else false
	 */
	public boolean approveStudentRegistration(int studentID);

	/**
	 * Method to add a course to the course list
	 * 
	 * @param course - course to add
	 * @throws CourseFoundException
	 * @throws ProfessorNotFoundException
	 */
	public void addCourse(Course course) throws CourseFoundException, ProfessorNotFoundException;

	/**
	 * Method to remove a course to the course list
	 * 
	 * @param courseId - id of the course to remove
	 * @return returns true if course removal successful or else false
	 */
	public boolean removeCourse(int courseId);

	/**
	 * Method to generate report card for a student
	 * 
	 * @param studentID - id of student to generate report card for
	 * @return list of grades for the student
	 * @throws GradeNotAddedException
	 */
	public ArrayList<Grade> generateReportCard(int studentID) throws GradeNotAddedException;

	/**
	 * Method to get list of all the courses
	 * 
	 * @return list of courses
	 */
	public ArrayList<Course> getAllCourseList();
}
