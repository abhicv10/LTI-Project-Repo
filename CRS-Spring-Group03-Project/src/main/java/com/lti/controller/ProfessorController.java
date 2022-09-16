package com.lti.controller;

import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lti.bean.Student;
import com.lti.exception.CourseNotAssignedToProfessorException;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.StudentNotFoundException;
import com.lti.service.ProfessorInterfaceOperation;
import com.lti.service.ProfessorService;

/**
 * @author 10710133
 *
 */

@RestController
public class ProfessorController {

	/**
	 * Method to add Grade
	 * 
	 * @param studentID - id of the student to be graded
	 * @param courseID  - id of the course to be graded
	 * @param grade     - Grade assigned by professor
	 * @throws CourseNotFoundException
	 * @throws StudentNotFoundException
	 */

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/add-grade/{studentID}/{courseID}/{grade}")
	public ResponseEntity<Object> addGrade(@PathVariable("studentID") int studentID,
			@PathVariable("courseID") int courseID, @PathVariable("grade") String grade)
			throws StudentNotFoundException, CourseNotFoundException {
		ProfessorInterfaceOperation service = new ProfessorService();
		service.addGrade(studentID, courseID, grade);
		return new ResponseEntity<>("grade added successfully!", HttpStatus.OK);
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

	/**
	 * Method to view students enrolled for a course
	 * 
	 * @param professorID - id of the professor
	 * @param courseID    - id of the course to be viewed
	 * @return list of students enrolled in a course
	 * @throws CourseNotAssignedToProfessorException
	 * @throws CourseNotFoundException
	 */

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/view-students-enrolled/{courseID}/{profID}")
	public ArrayList<Student> viewStudentsEnrolled(@PathVariable("courseID") int courseID,
			@PathVariable("profID") int profID) throws CourseNotFoundException, CourseNotAssignedToProfessorException {
		ProfessorInterfaceOperation service = new ProfessorService();
		return service.viewStudentsEnrolled(courseID, profID);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ CourseNotAssignedToProfessorException.class })
	public ResponseEntity<Object> courseNotAssignedToProfessorExceptionHandler(
			CourseNotAssignedToProfessorException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
