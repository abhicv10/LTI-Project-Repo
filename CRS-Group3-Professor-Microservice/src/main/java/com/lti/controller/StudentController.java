/**
 * 
 */
package com.lti.controller;

import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lti.bean.Course;
import com.lti.bean.Grade;
import com.lti.bean.Payment;
import com.lti.service.StudentInterfaceOperation;
import com.lti.service.StudentService;
import com.lti.exception.*;;

/**
 * @author 10710133
 *
 */
@RestController
public class StudentController {

	/**
	 * Method to add a course for a student
	 * 
	 * @param studentID - id of the student
	 * @param courseID  - id of the course to be added
	 * @return response entity object
	 * @throws StudentNotFoundException
	 * @throws CourseNotFoundException
	 * @throws CourseLimitExceedException
	 * @throws CourseAlreadyTakenException
	 */

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/add-course/{studentID}/{courseID}")
	public ResponseEntity<Object> addCourse(@PathVariable("studentID") int studentID,
			@PathVariable("courseID") int courseID) throws StudentNotFoundException, CourseNotFoundException,
			CourseLimitExceedException, CourseAlreadyTakenException {
		StudentInterfaceOperation service = new StudentService();
		service.addCourse(studentID, courseID);
		return new ResponseEntity<Object>("course added successfully", HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ StudentNotFoundException.class })
	public ResponseEntity<Object> studentNotFoundExceptionHandler(StudentNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ CourseNotFoundException.class })
	public ResponseEntity<Object> courseNotFoundExceptionHandler(CourseNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler({ CourseLimitExceedException.class })
	public ResponseEntity<Object> courseLimitExceedExceptionHandler(CourseLimitExceedException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler({ CourseAlreadyTakenException.class })
	public ResponseEntity<Object> courseAlreadyTakenExceptionHandler(CourseAlreadyTakenException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	}

	/**
	 * Method to drop a course for a student
	 * 
	 * @param studentID - id of the student
	 * @param courseID  - id of the course to be dropped
	 * @return response entity object
	 */

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.DELETE, value = "/drop-course/{studentID}/{courseID}")
	public ResponseEntity<Object> dropCourse(@PathVariable("studentID") int studentID,
			@PathVariable("courseID") int courseID) throws StudentNotFoundException, CourseNotFoundException {
		StudentInterfaceOperation service = new StudentService();
		service.dropCourse(studentID, courseID);
		return new ResponseEntity<>("course droped successfully!", HttpStatus.OK);
	}

	/**
	 * Method to view enrolled courses of a student
	 * 
	 * @param studentID - id of the student
	 * @return list of enrolled courses of the student
	 * @throws StudentNotFoundException
	 * @throws CourseNotFoundException
	 */

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/view-enrolled-courses/{studentID}")
	public ArrayList<Course> viewEnrolledCourses(@PathVariable("studentID") int studentID)
			throws StudentNotFoundException {
		StudentInterfaceOperation service = new StudentService();
		return service.viewEnrolledCourses(studentID);
	}

	/**
	 * Method to view grades for a student
	 * 
	 * @param studentID - id of the the student
	 * @return list of grades for all enrolled courses of the student
	 * @throws GradeNotAddedException
	 */

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/view-grades/{studentID}")
	public ArrayList<Grade> viewGrades(@PathVariable("studentID") int studentID) throws GradeNotAddedException {
		StudentInterfaceOperation service = new StudentService();
		return service.viewGrades(studentID);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ GradeNotAddedException.class })
	public ResponseEntity<Object> gradeNotAddedExceptionHandler(GradeNotAddedException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Method to pay the fee of a student
	 * 
	 * @param payment - payment details
	 * @return response entity object
	 * @throws PaymentAlreadyDoneException
	 */

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/payfee")
	public ResponseEntity<Object> payFee(@RequestBody Payment payment) throws PaymentAlreadyDoneException {
		StudentInterfaceOperation service = new StudentService();
		service.payFee(payment);
		return new ResponseEntity<>("payment suucessful!", HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler({ PaymentAlreadyDoneException.class })
	public ResponseEntity<Object> PaymentAlreadyDoneExceptionHandler(PaymentAlreadyDoneException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
	}
}
