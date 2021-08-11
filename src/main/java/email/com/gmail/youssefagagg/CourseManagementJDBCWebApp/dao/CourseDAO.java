package email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean.Course;
import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean.Teacher;
import email.com.gmail.youssefagagg.db.connection.DatabaseConnectionFactory;

public class CourseDAO {
	
	public void addCourse(Course course) throws SQLException {
		final String sql = "insert into Course (name, credits,Teacher_id) values (?,?,?)";
		
		try(Connection con=
				DatabaseConnectionFactory.getConnectionFactory()
				.getConnection();
			PreparedStatement preparedStatement=
				con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			preparedStatement.setString(1, course.getName());
			preparedStatement.setInt(2, course.getCredits());
			
			if(course.getTeacher()==null) {
				preparedStatement.setNull(3, Types.INTEGER);
			}else {
				preparedStatement.setInt(3, course.getTeacher().getId());
				
			}
			preparedStatement.execute();
			try(ResultSet resultSet=preparedStatement.getGeneratedKeys()){
				if(resultSet.next())course.setId(resultSet.getInt(1));
			}
			
		}
		
	}
	public void deleteCourse(int id) throws SQLException {
final String sql = "delete from course where id=?";
		
		try(Connection con=
				DatabaseConnectionFactory.getConnectionFactory()
				.getConnection();
			PreparedStatement preparedStatement=
				con.prepareStatement(sql)){
			
			preparedStatement.setInt(1, id);
			
			
			preparedStatement.executeUpdate();
			
			
		}
	}

	public void updateCorse(Course course)throws SQLException{
		String sql="Update course SET"
				+ "name=?"
				+ "credits=?"
				+ "Teacher_id=?"
				+ "where id=?";
		try(Connection con=
				DatabaseConnectionFactory.getConnectionFactory()
				.getConnection();
			PreparedStatement preparedStatement=
				con.prepareStatement(sql)){
			preparedStatement.setString(1, course.getName());
			preparedStatement.setInt(2, course.getCredits());
			preparedStatement.setInt(3, course.getTeacher().getId());
			preparedStatement.setInt(4, course.getId());
			preparedStatement.executeUpdate();
			
		}
		
	}
	public Course getCourse(int id) throws SQLException {
		StringBuilder sb = new StringBuilder("select course.id as courseId, course.name as courseName,") 
				.append("course.credits as credits, Teacher.id as teacherId,Teacher.first_name as firstName, ")
				.append("Teacher.last_name as lastName, Teacher.designation as designation ")
		.append("from Course left outer join Teacher on ")
		.append("course.Teacher_id = Teacher.id ")
		.append("where course.id=?");
		Course course=null;
		try(Connection con=
				DatabaseConnectionFactory.getConnectionFactory()
				.getConnection();
			PreparedStatement preparedStatement=
				con.prepareStatement(sb.toString())){
			
			preparedStatement.setInt(1, id);
			
			try(ResultSet rs=preparedStatement.executeQuery()){
				while(rs.next()) {
					course = new Course();
					course.setId(rs.getInt("courseId"));
					course.setName(rs.getString("courseName"));
					course.setCredits(rs.getInt("credits"));
					
					int teacherId = rs.getInt("teacherId");
					//check whether teacher id was null in the table
					if (rs.wasNull()) //no teacher set for this course.
					continue;
					Teacher teacher = new Teacher();
					teacher.setId(teacherId);
					teacher.setFirstName(rs.getString("firstName"));
					teacher.setLastName(rs.getString("lastName"));
					teacher.setDesignation(rs.getString("designation"));
					course.setTeacher(teacher);
					
				}
			}
			
			
		}
		return course;
		
	}
	public List<Course> getCourses () throws SQLException{
		List<Course> list=new ArrayList<>();
		//create SQL statement using left outer join
		StringBuilder sb = new StringBuilder("select course.id as courseId, course.name as courseName,") 
				.append("course.credits as credits, Teacher.id as teacherId,Teacher.first_name as firstName, ")
				.append("Teacher.last_name as lastName, Teacher.designation as designation ")
		.append("from Course left outer join Teacher on ")
		.append("course.Teacher_id = Teacher.id ")
		.append("order by course.name");
		try(Connection con=
				DatabaseConnectionFactory.getConnectionFactory()
				.getConnection();
			Statement statement=
				con.createStatement()){
			
			try(ResultSet rs=statement.executeQuery(sb.toString())){
				while(rs.next()) {
					Course course = new Course();
					course.setId(rs.getInt("courseId"));
					course.setName(rs.getString("courseName"));
					course.setCredits(rs.getInt("credits"));
					list.add(course);
					int teacherId = rs.getInt("teacherId");
					//check whether teacher id was null in the table
					if (rs.wasNull()) //no teacher set for this course.
					continue;
					Teacher teacher = new Teacher();
					teacher.setId(teacherId);
					teacher.setFirstName(rs.getString("firstName"));
					teacher.setLastName(rs.getString("lastName"));
					teacher.setDesignation(rs.getString("designation"));
					course.setTeacher(teacher);
					
				}
			}
			
		}
		return list;
	}

}
