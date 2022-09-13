/**
 * 
 */
package com.lti.service;

import java.util.ArrayList;

import com.lti.bean.Course;
import com.lti.bean.Grade;
import com.lti.bean.Login;
import com.lti.bean.Professor;

/**
 * @author 10710133
 *
 */

public interface AdminInterfaceOperation {
	
	/**
	 * Method to add a professor
	 * @param professor - professsor to add
	 * @param login - login details to create a new user
	 */
	public void addProfessor(Professor professor, Login login);
	
	/**
	 * Method to approve registration of a student
	 * @param studentID - id of the student to approve
	 */
	public void approveStudentRegistration(int studentID);
	
	/**
	 * Method to add a course to the course list
	 * @param course - course to add
	 */
	public void addCourse(Course course);
	
	/**
	 * Method to remove a course to the course list
	 * @param courseId - id of the course to remove
	 */
	public void removeCourse(int courseId);
	
	/**
	 * Method to generate report card for a student
	 * @param studentID - id of student to generate report card for
	 * @return list of grades for the student
	 */
	public ArrayList<Grade> generateReportCard(int studentID);
}
