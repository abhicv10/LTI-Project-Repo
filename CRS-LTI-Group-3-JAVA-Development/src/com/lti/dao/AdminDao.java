/**
 * 
 */
package com.lti.dao;


import com.lti.bean.Course;
import com.lti.bean.Grade;
import com.lti.exception.*;
import java.util.ArrayList;

/**
 * @author 10710133
 *
 */

public interface AdminDao {

    /**
     * Method to add an admin
     */
    public void addAdmin();
    
    /**
	 * Method to add a course to course table
	 * @param course - course to be added
	 * @throws CourseFoundException
	 * @throws ProfessorNotFoundException
	 */
	public void addCourse(Course course) throws CourseFoundException, ProfessorNotFoundException;

    /**
     * Method to removes a course from the course table
     * @param courseID - id of course to be removec from course table
     */
    public void removeCourse(int courseID);
    
    /**
     * Method to get grades of a particular student
     * @param studentID - id of student to get grade list for
     * @return list of grades
     */
    public ArrayList<Grade> getGrades(int studentID);
}
