package email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean;

import java.sql.SQLException;

import java.util.List;

import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.dao.CourseDAO;
import lombok.Data;
import lombok.ToString;

@Data
public class Course {
	private int id;
	private String name;
	private int credits;
	private Teacher teacher;
	private List<Student>students;
	private CourseDAO courseDAO = new CourseDAO();
	public boolean isValidCourse() {
		return name != null && credits != 0;
		}
	public void addCourse() throws SQLException {
		courseDAO.addCourse(this);
		}
	public List<Course> getCourses() throws SQLException {
		return courseDAO.getCourses();
		}
	public String toString() {
		return name;
	}


}
