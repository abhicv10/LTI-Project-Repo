package com.lti.controller;

import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lti.bean.Course;
import com.lti.bean.Professor;
import com.lti.exception.CourseFoundException;
import com.lti.exception.CourseNotFoundException;
import com.lti.exception.GradeNotAddedException;
import com.lti.exception.ProfessorNotFoundException;
import com.lti.exception.StudentNotFoundException;
import com.lti.exception.UserAlreadyExistException;
import com.lti.service.AdminInterfaceOperation;
import com.lti.service.AdminService;

/**
 * @author 10710133
 *
 */

@RestController
public class AdminController {

	/**
	 * Method to add the professor
	 * 
	 * @param professor - Professor details
	 * @param login     - login details
	 * @return response entity object
	 * @throws UserAlreadyExistException
	 */

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/add-professor")
	public ResponseEntity<Object> addProfessor(@RequestBody Professor professor) throws UserAlreadyExistException {
		AdminInterfaceOperation service = new AdminService();
		boolean result = service.addProfessor(professor);

		if (!result) {
			throw new UserAlreadyExistException("Professor already registered!");
		} else {
			return new ResponseEntity<>("professor added successfully!", HttpStatus.OK);
		}
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler({ UserAlreadyExistException.class })
	public ResponseEntity<Object> userAlreadyExistExceptionHandler(UserAlreadyExistException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
	}

	/**
	 * Method to approve the student registration
	 * 
	 * @param studentID - id of the student
	 * @return response entity object
	 * @throws StudentNotFoundException
	 */

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.PUT, value = "/approve-student/{studentID}")
	public ResponseEntity<Object> approveStudentRegistration(@PathVariable("studentID") int studentID)
			throws StudentNotFoundException {
		AdminInterfaceOperation service = new AdminService();
		boolean result = service.approveStudentRegistration(studentID);
		if (!result) {
			throw new StudentNotFoundException("student not found!");
		} else {
			return new ResponseEntity<>("Student approved successfully!", HttpStatus.OK);
		}
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ StudentNotFoundException.class })
	public ResponseEntity<Object> studentNotFoundExceptionHandler(StudentNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Method to add course
	 * 
	 * @param course - The course details
	 * @return response entity object
	 * @throws CourseFoundException
	 * @throws ProfessorNotFoundException
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.POST, value = "/add-course")
	public ResponseEntity<Object> addCourse(@RequestBody Course course)
			throws ProfessorNotFoundException, CourseFoundException {
		AdminInterfaceOperation service = new AdminService();
		service.addCourse(course);
		return new ResponseEntity<>("course added successfully!", HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler({ CourseFoundException.class })
	public ResponseEntity<Object> courseFoundExceptionHandler(CourseFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	}

	@ResponseStatus(HttpStatus.FOUND)
	@ExceptionHandler({ ProfessorNotFoundException.class })
	public ResponseEntity<Object> professorNotFoundExceptionHandler(ProfessorNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.FOUND);
	}

	/**
	 * Method to remove course
	 * 
	 * @param courseID - course ID of the required course
	 * @return response entity object
	 * @throws CourseNotFoundException
	 */

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.DELETE, value = "/remove-course/{courseID}")
	public ResponseEntity<Object> removeCourse(@PathVariable("courseID") int courseId) throws CourseNotFoundException {
		AdminInterfaceOperation service = new AdminService();
		boolean result = service.removeCourse(courseId);

		if (!result) {
			throw new CourseNotFoundException("Course not found!");
		} else {
			return new ResponseEntity<>("Course removed successfully!", HttpStatus.OK);
		}
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler({ CourseNotFoundException.class })
	public ResponseEntity<Object> courseNotFoundExceptionHandler(CourseNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	}

	/**
	 * Method to generate the report card of the student
	 * 
	 * @param studentID - studentID of the required student
	 * @return returns the generated report card of that particular student
	 */

	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/generate-reportcard/{studentID}")
	public ResponseEntity<Object> generateReportCard(@PathVariable("studentID") int studentID)
			throws GradeNotAddedException {
		AdminInterfaceOperation service = new AdminService();
		return new ResponseEntity<>(service.generateReportCard(studentID), HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler({ GradeNotAddedException.class })
	public ResponseEntity<Object> gradeNotAddedExceptionHandler(GradeNotAddedException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	}

	/**
	 * Method to get list of all courses
	 * 
	 * @return- returns list of courses
	 */
	@RequestMapping(produces = MediaType.APPLICATION_JSON, method = RequestMethod.GET, value = "/course-list")
	public ArrayList<Course> getCourseList() {
		AdminInterfaceOperation service = new AdminService();
		return service.getAllCourseList();
	}
}
