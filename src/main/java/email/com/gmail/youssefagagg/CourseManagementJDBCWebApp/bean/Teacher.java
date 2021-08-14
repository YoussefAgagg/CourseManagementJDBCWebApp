package email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean;

import java.sql.SQLException;
import java.util.List;

import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.dao.TeacherDAO;

public class Teacher extends Person {
	private String designation;
	private TeacherDAO teacherDAO=new TeacherDAO();
	public void addTeacher() throws SQLException {
		teacherDAO.addTeacher(this);;
		}
	public List<Teacher> getTeachers() throws SQLException {
		return teacherDAO.getTeachers();
		}
	public void updateTeacher() throws SQLException {
		teacherDAO.updateTeacher(this);
		
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
}
