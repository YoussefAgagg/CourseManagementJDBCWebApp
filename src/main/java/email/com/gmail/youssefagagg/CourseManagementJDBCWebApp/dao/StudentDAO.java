package email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean.Course;
import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean.Student;
import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean.Teacher;
import email.com.gmail.youssefagagg.db.connection.DatabaseConnectionFactory;

public class StudentDAO {
	public void addStudent (Student student) throws SQLException {
		//get connection from connection pool
		Connection con = DatabaseConnectionFactory.getConnectionFactory().getConnection();
		try {
			String sql = "insert into Student (first_name,last_name,enrolled_since) values (?,?,?)";
			//create prepared statement with option to get auto generated keys
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//set parameters
			stmt.setString(1, student.getFirstName());
			stmt.setString(2, student.getLastName());
			stmt.setLong(3, student.getEnrolledsince());
			
			
			stmt.execute();
			
			//Get auto generated keys
	        ResultSet rs = stmt.getGeneratedKeys(); 
	        
	        if (rs.next())
	        	student.setId(rs.getInt(1));
	        rs.close();
	        stmt.close();
	        insertIntoCourseStudentTable(con,student);
		} finally {
			con.close();
		}
	}
	public void deleteStudent(int id) throws SQLException {
final String sql = "delete from student where id=?";
		
		try(Connection con=
				DatabaseConnectionFactory.getConnectionFactory()
				.getConnection();
			PreparedStatement preparedStatement=
				con.prepareStatement(sql)){
			
			preparedStatement.setInt(1, id);
			
			
			preparedStatement.executeUpdate();
			
			
		}
	}

	public void updateStudent(Student student)throws SQLException{
		String sql="Update Student SET"
				+ "first_name=?"
				+ "last_name=?"
				+ "enrolled_since=?"
				+ "where id=?";
		try(Connection con=
				DatabaseConnectionFactory.getConnectionFactory()
				.getConnection();
			PreparedStatement preparedStatement=
				con.prepareStatement(sql)){
			preparedStatement.setString(1, student.getFirstName());
			preparedStatement.setString(2, student.getLastName());
			preparedStatement.setLong(3, student.getEnrolledsince());
			preparedStatement.setInt(4, student.getId());
			preparedStatement.executeUpdate();
			updateCourseStudentTable(con,student);
			
		}
		
	}
	private void updateCourseStudentTable(Connection con, Student student) throws SQLException {
		String sql="delete from course_student where Student_id=?";
		PreparedStatement preparedStatement=con.prepareStatement(sql);
		preparedStatement.setInt(1, student.getId());
		preparedStatement.executeUpdate();
		preparedStatement.close();
		insertIntoCourseStudentTable(con, student);
		
	}
	private void insertIntoCourseStudentTable(Connection con,Student student) throws SQLException {
		String sql = "insert into course_student (Course_id,Student_id) values (?,?)";
		//create prepared statement with option to get auto generated keys
		PreparedStatement stmt = con.prepareStatement(sql);
		//set parameters
		List<Course>courses=student.getCourses();
		for(Course course:courses) {
		stmt.setInt(1, course.getId());
		stmt.setInt(2, student.getId());
		stmt.execute();
		}
		stmt.close();
		
	}

	public List<Student> getStudents () throws SQLException {
	
		List<Student> students = new ArrayList<>();
		
			
			String sql = "select distinct student.id,student.first_name,student.last_name,student.enrolled_since,course.id,course.name,course.credits from student left outer join  course_student on student.id =course_student.Student_id left outer join course on course_student.Course_id=course.id"
					;
			Map<Integer, Student>map=new HashMap<Integer, Student>();
			try(Connection con=
					DatabaseConnectionFactory.getConnectionFactory()
					.getConnection();
				Statement statement=
					con.createStatement()){
				
				try(ResultSet rs=statement.executeQuery(sql)){
					while(rs.next()) {
						int studentid=rs.getInt(1);
						Student student=map.get(studentid);
						if(student==null) {
						 student=new Student();
						student.setId(studentid);
						student.setFirstName(rs.getString(2));
						student.setLastName(rs.getString(3));
						student.setEnrolledsince(rs.getLong(4));
						student.setCourses(new ArrayList<Course>());
						map.put(studentid, student);
						}

						int courseid = rs.getInt(5);
						//check whether teacher id was null in the table
						if (rs.wasNull()) //no teacher set for this course.
						continue;
						Course course = new Course();
						course.setId(courseid);
						course.setName(rs.getString(6));
						course.setCredits(rs.getInt(7));
						student.getCourses().add(course);
						//course.setTeacher(teacher);
						
					}
				}
				
			}
			students.addAll(map.values());
			return students;
		
	}

	public Student getStudent(int id) throws SQLException {
		
		String sql = "select distinct student.id,student.first_name,student.last_name,student.enrolled_since,course.id,course.name,course.credits from student left outer join  course_student on student.id =course_student.Student_id left outer join course on course_student.Course_id=course.id where student.id=?"
				;
		Student student = null;	
		
		try(Connection con=
				DatabaseConnectionFactory.getConnectionFactory()
				.getConnection();
			PreparedStatement preparedStatement=
				con.prepareStatement(sql)){
			preparedStatement.setInt(1, id);
			
			try(ResultSet rs=preparedStatement.executeQuery()){
				if(rs.next()) {
					 student = new Student();
					int studentid=rs.getInt(1);
						student.setId(studentid);
						student.setFirstName(rs.getString(2));
						student.setLastName(rs.getString(3));
						student.setEnrolledsince(rs.getLong(4));
						student.setCourses(new ArrayList<Course>());
				}
				while(rs.next()) {
					int courseid = rs.getInt(5);
					//check whether teacher id was null in the table
					if (rs.wasNull()) //no teacher set for this course.
					continue;
					Course course = new Course();
					course.setId(courseid);
					course.setName(rs.getString(6));
					course.setCredits(rs.getInt(7));
					student.getCourses().add(course);
					//course.setTeacher(teacher);
					
				}
			}
			
		}
		
		
		return student;
	}
}
