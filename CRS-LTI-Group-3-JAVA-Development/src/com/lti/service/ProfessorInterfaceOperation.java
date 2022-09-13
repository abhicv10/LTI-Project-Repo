/**
 * 
 */
package com.lti.service;

import java.util.ArrayList;
import com.lti.bean.Student;

/**
 * @author 10710133
 *
 */
public interface ProfessorInterfaceOperation {
	/**
	 * Method to add grade to a student for a particular course
	 * @param studentID - Gets id of student to be graded
	 * @param courseID - Gets id of course to be graded
	 * @param grade - Gets grade for a particular student for a particular course
	 */
	public void addGrade(int studentID, int courseID, String grade);
	
	/**
	 * Method to get list of students enrolled for particular course taken by a professor
	 * @param courseID - Gets id of course 
	 * @param profID - Gets id of professor 
	 * @return List of students enrolled for a particular course
	 */
	public ArrayList<Student> viewStudentsEnrolled(int courseID, int profID);
}
