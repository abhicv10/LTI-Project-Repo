package com.lti.service;

import java.util.ArrayList;

import com.lti.bean.Student;
import com.lti.dao.ProfessorDaoImplementation;
import com.lti.exception.CourseNotAssignedToProfessorException;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.ProfessorNotFoundException;
import com.lti.exception.StudentNotFoundException;

/**
 * @author 10710133
 *
 */

public class ProfessorService implements ProfessorInterfaceOperation {

	public void addGrade(int studentID, int courseID, String grade)
			throws StudentNotFoundException, CourseNotFoundException {
		ProfessorDaoImplementation profDao = new ProfessorDaoImplementation();
		profDao.addGrade(studentID, courseID, grade);
	}

	public ArrayList<Student> viewStudentsEnrolled(int courseID, int profID)
			throws CourseNotFoundException, CourseNotAssignedToProfessorException, ProfessorNotFoundException {
		ProfessorDaoImplementation profDao = new ProfessorDaoImplementation();
		return profDao.getStudentList(courseID, profID);
	}
}