/**
 * 
 */
package com.lti.dao;

import com.lti.bean.Professor;
import java.util.ArrayList;

import com.lti.bean.Student;

import com.lti.exception.CourseNotAssignedToProfessorException;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.ProfessorNotFoundException;
import com.lti.exception.StudentNotFoundException;

import com.lti.bean.Course;

/**
 * @author 10710133
 *
 */

public interface ProfessorDao {
	/**
	 * Method to add new professor
	 * 
	 * @param professor - Gets details of professor to be added
	 * @return Id of the professor added
	 */
	public int addProfessor(Professor professor);

	/**
	 * Method to get the list of professors
	 * 
	 * @return List of professors
	 */
	public ArrayList<Professor> getProfessorList();

	/**
	 * Method to get the list of courses taken by a professor
	 * 
	 * @param profID - Id of the professor whose list of courses is to be displayed
	 * @return list of courses taken by a professor
	 * @throws ProfessorNotFoundException
	 */
	public ArrayList<Course> getCourseList(int profID) throws ProfessorNotFoundException;

	/**
	 * Method to get the list of students in a particular course taken by a
	 * professor
	 * 
	 * @param courseID - Id of the course
	 * @param profID   - Id of the professor
	 * @return list of students in a particular course
	 * @throws CourseNotFoundException
	 * @throws CourseNotAssignedToProfessorException
	 */
	public ArrayList<Student> getStudentList(int courseID, int profID)
			throws CourseNotFoundException, CourseNotAssignedToProfessorException;

	/**
	 * Method to add grade for a student for a particular course
	 * 
	 * @param studentID - Id of the student graded
	 * @param courseID  - Id of the course graded
	 * @param grade     - Grade assigned to the student for a particular course
	 * @throws StudentNotFoundException
	 * @throws CourseNotFoundException
	 */
	public void addGrade(int studentID, int courseID, String grade)
			throws StudentNotFoundException, CourseNotFoundException;

}
