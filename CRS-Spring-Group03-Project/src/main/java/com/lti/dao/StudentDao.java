/**
 * 
 */
package com.lti.dao;

import java.util.ArrayList;

import com.lti.bean.Payment;
import com.lti.bean.Student;
import com.lti.exception.StudentNotFoundException;

/**
 * @author 10710133
 *
 */
public interface StudentDao {

	/**
	 * Method to Add Student
	 * 
	 * @param studentID
	 * @return a StudentId
	 * @throws StudentNotFoundException
	 */
	public int addStudent(Student student);

	/**
	 * Method to Approve Student
	 * 
	 * @param studentID
	 * @throws StudentNotFoundException
	 */
	public void approveStudent(int studentID) throws StudentNotFoundException;

	/**
	 * Method to Check wheather a student is approved or not
	 * 
	 * @param studentID
	 * @return boolean
	 * @throws StudentNotFoundException
	 */
	public boolean isStudentApproved(int studentID) throws StudentNotFoundException;

	/**
	 * Method to Get the student list
	 * 
	 * @return student list
	 */
	public ArrayList<Student> getStudentList();

	/**
	 * Method to make payment
	 * 
	 * @param payment - Get the payment bean
	 */
	public void payFee(Payment payment);
}
