package com.lti.service;

import java.util.ArrayList;

import com.lti.bean.Course;
import com.lti.bean.Grade;
import com.lti.bean.Payment;

public interface StudentInterfaceOperation {

	/**
	 * Method to Register course for a student
	 * @param studentID - Get id of student
	 */
	public void registerCourses(int studentID);

	/**
	 * Method to Adds a course to student course list
	 * @param studentID - Get id of student
	 * @param courseID  - Get id of course
	 */
	public void addCourse(int studentID, int courseID);

	/**
	 * Method to Drops a course to student course list
	 * @param studentID - Get id of student
	 * @param courseID  - Get id of course
	 */
	public void dropCourse(int studentID, int courseID);

	/**
	 * Method to Get list of courses enrolled by the student
	 * @param studentID - Get id of student
	 * @return list of enrolled courses
	 */
	public ArrayList<Course> viewEnrolledCourses(int studentID);

	/**
	 * Method to Get list of grades for a student
	 * @param studentID - Get id of student
	 * @return list of grades
	 */
	public ArrayList<Grade> viewGrades(int studentID);

	/**
	 * Method to Pays fee for a student
	 * @param payment - Get payment bean
	 */
	public void payFee(Payment payment);
}
