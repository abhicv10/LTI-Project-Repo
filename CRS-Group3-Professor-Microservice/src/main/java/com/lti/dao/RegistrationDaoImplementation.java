package com.lti.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.lti.bean.Course;
import com.lti.bean.Student;
import com.lti.constant.SQLConstant;
import com.lti.utils.DbUtils;

import com.lti.exception.*;

@Repository
public class RegistrationDaoImplementation {

	Connection conn = null;

	public ArrayList<Course> getCourseList() {

		ArrayList<Course> courses = new ArrayList<Course>();

		PreparedStatement stmt = null;

		try {

			conn = DbUtils.getConnection();
			stmt = conn.prepareStatement(SQLConstant.GET_COURSE_LIST);
			ResultSet queryResult = stmt.executeQuery(SQLConstant.GET_COURSE_LIST);

			while (queryResult.next()) {

				int id = queryResult.getInt("courseID");
				String courseName = queryResult.getString("courseName");
				String details = queryResult.getString("courseDetails");
				int profID = queryResult.getInt("professorID");
				int enrolledStudentCount = queryResult.getInt("enrolledStudentCount");

				Course course = new Course();
				course.setCourseID(id);
				course.setCourseName(courseName);
				course.setCourseDetails(details);
				course.setProfID(profID);
				course.setEnrolledStudentCount(enrolledStudentCount);
				courses.add(course);
			}

			stmt.close();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
		}

		return courses;
	}

	public void registerCourse(int studentID, int courseID) throws StudentNotFoundException, CourseNotFoundException,
			CourseLimitExceedException, CourseAlreadyTakenException {

		PreparedStatement stmt = null;

		try {
			// to check if student id exist
			StudentDaoImplementation stuDao = new StudentDaoImplementation();
			ArrayList<Student> stu = stuDao.getStudentList();

			boolean studentIDFound = false;
			for (Student s : stu) {
				if (s.getId() == studentID) {
					studentIDFound = true;
					break;
				}
			}

			if (!studentIDFound) {
				throw new StudentNotFoundException("student id doesn't exist");
			}

			// to check if course id exist
			RegistrationDaoImplementation couDao = new RegistrationDaoImplementation();

			ArrayList<Course> cou = couDao.getCourseList();

			boolean courseIDFound = false;
			for (Course c : cou) {
				if (c.getCourseID() == courseID) {
					courseIDFound = true;
					break;
				}
			}

			if (!courseIDFound) {
				throw new CourseNotFoundException("course id doesn't exist");
			}

			conn = DbUtils.getConnection();

			String sql = String.format(SQLConstant.CHECK_IF_COURSE_TAKEN_BY_STUDENT, studentID, courseID);
			stmt = conn.prepareStatement(sql);
			ResultSet result = stmt.executeQuery(sql);
			result.next();
			int courseAlreadyTaken = result.getInt(1);

			if (courseAlreadyTaken == 1) {
				throw new CourseAlreadyTakenException("course already registered by the student");
			}

			stmt.close();

			// check availability
			sql = String.format(SQLConstant.GET_COURSE_STUDNET_COUNT, courseID);
			stmt = conn.prepareStatement(sql);

			result = stmt.executeQuery(sql);
			result.next();
			int enrolledStudentCount = result.getInt(1);

			if (enrolledStudentCount == 10) {
				throw new CourseLimitExceedException("course limit reached, cannot enroll for this course");
			}

			stmt.close();

			// increment student count for course
			enrolledStudentCount += 1;
			sql = String.format(SQLConstant.UPDATE_ENROLLED_STUDENT_COUNT, enrolledStudentCount, courseID);
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
			stmt.close();

			stmt = conn.prepareStatement(SQLConstant.REGISTER_COURSE);
			stmt.setInt(1, studentID);
			stmt.setInt(2, courseID);
			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
		}
	}

	public void deRegisterCourse(int studentID, int courseID) throws StudentNotFoundException, CourseNotFoundException {

		PreparedStatement stmt = null;

		try {

			StudentDaoImplementation stuDao = new StudentDaoImplementation();
			ArrayList<Student> students = stuDao.getStudentList();

			boolean studentIDFound = false;
			for (Student s : students) {
				if (s.getId() == studentID) {
					studentIDFound = true;
					break;
				}
			}

			if (!studentIDFound) {
				throw new StudentNotFoundException("student id doesn't exist");
			}

			// to check if course id exist
			RegistrationDaoImplementation couDao = new RegistrationDaoImplementation();

			ArrayList<Course> courses = couDao.getCourseList();

			boolean courseIDFound = false;
			for (Course c : courses) {
				if (c.getCourseID() == courseID) {
					courseIDFound = true;
					break;
				}
			}

			if (!courseIDFound) {
				throw new CourseNotFoundException("sourse id doesn't exist");
			}

			conn = DbUtils.getConnection();
			String sql = String.format(SQLConstant.DEREGISTER_COURSE, courseID, studentID);
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
		}
	}

	public ArrayList<Course> getStudentCourseList(int studentID) throws StudentNotFoundException {

		ArrayList<Course> courses = new ArrayList<Course>();
		ArrayList<Integer> courseIDs = new ArrayList<Integer>();

		PreparedStatement stmt = null;

		try {

			StudentDaoImplementation stuDao = new StudentDaoImplementation();
			ArrayList<Student> students = stuDao.getStudentList();

			boolean studentIDFound = false;
			for (Student s : students) {
				if (s.getId() == studentID) {
					studentIDFound = true;
					break;
				}
			}

			if (!studentIDFound) {
				throw new StudentNotFoundException("student id doesn't exist");
			}

			conn = DbUtils.getConnection();
			String sql = String.format(SQLConstant.GET_STUDENT_COURSE_LIST, studentID);
			stmt = conn.prepareStatement(sql);
			ResultSet queryResult = stmt.executeQuery(sql);

			while (queryResult.next()) {
				int id = queryResult.getInt("courseID");
				courseIDs.add(id);
			}

			for (int n : courseIDs) {
				String sql1 = String.format(SQLConstant.GET_COURSE_DETAILS, n);
				stmt = conn.prepareStatement(sql1);

				ResultSet queryResult1 = stmt.executeQuery(sql1);
				queryResult1.next();
				String name = queryResult1.getString("courseName");

				Course course = new Course();
				course.setCourseName(name);
				course.setCourseID(n);

				courses.add(course);
			}

			stmt.close();

			return courses;

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
		}
		return courses;
	}
}
