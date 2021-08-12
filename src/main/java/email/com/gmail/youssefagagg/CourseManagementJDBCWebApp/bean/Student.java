package email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean;

import java.sql.SQLException;
import java.util.List;

import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.dao.StudentDAO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Student extends Person {
	private long enrolledsince;
	private List<Course>courses;
	private StudentDAO studentDAO=new StudentDAO();
	public void addStudent() throws SQLException {
		studentDAO.addStudent(this);
		
	}
	public List<Student> getStudents() throws SQLException {
		return studentDAO.getStudents();
		
	}
	public void updateStudent() throws SQLException {
		studentDAO.updateStudent(this);
		
	}
}
