/**
 * 
 */
package com.lti.service;

import java.util.ArrayList;
import com.lti.bean.Student;
import com.lti.exception.CourseNotAssignedToProfessorException;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.ProfessorNotFoundException;
import com.lti.exception.StudentNotFoundException;

/**
 * @author 10710133
 *
 */
public interface ProfessorInterfaceOperation {
	/**
	 * Method to add grade to a student for a particular course
	 * 
	 * @param studentID - id of student to be graded
	 * @param courseID  - id of course to be graded
	 * @param grade     - grade for a particular student for a particular course'
	 * @throws StudentNotFoundException
	 * @throws CourseNotFoundException
	 */
	public void addGrade(int studentID, int courseID, String grade)
			throws StudentNotFoundException, CourseNotFoundException;

	/**
	 * Method to get list of students enrolled for particular course taken by a
	 * professor
	 * 
	 * @param courseID - id of course
	 * @param profID   - id of professor
	 * @return list of students enrolled for a particular course
	 * @throws CourseNotFoundException
	 * @throws CourseNotAssignedToProfessorException
	 */
	public ArrayList<Student> viewStudentsEnrolled(int courseID, int profID)
			throws CourseNotFoundException, CourseNotAssignedToProfessorException, ProfessorNotFoundException;
}
