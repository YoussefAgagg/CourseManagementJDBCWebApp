package email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean.Teacher;
import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.db.connection.DatabaseConnectionFactory;


public class TeacherDAO {
	
	public void addTeacher (Teacher teacher) throws SQLException {
		//get connection from connection pool
		Connection con = DatabaseConnectionFactory.getConnectionFactory().getConnection();
		try {
			String sql = "insert into Teacher (first_name,last_name,designation) values (?,?,?)";
			//create prepared statement with option to get auto generated keys
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//set parameters
			stmt.setString(1, teacher.getFirstName());
			stmt.setString(2, teacher.getLastName());
			stmt.setString(3, teacher.getDesignation());
			
			stmt.execute();
			
			//Get auto generated keys
	        ResultSet rs = stmt.getGeneratedKeys(); 
	        
	        if (rs.next())
	        	teacher.setId(rs.getInt(1));
	        
	        rs.close();
	        stmt.close();
		} finally {
			con.close();
		}
	}
	public void deleteTeacher(int id) throws SQLException {
final String sql = "delete from Teacher where id=?";
		
		try(Connection con=
				DatabaseConnectionFactory.getConnectionFactory()
				.getConnection();
			PreparedStatement preparedStatement=
				con.prepareStatement(sql)){
			
			preparedStatement.setInt(1, id);
			
			
			preparedStatement.executeUpdate();
			
			
		}
	}

	public void updateTeacher(Teacher teacher)throws SQLException{
		String sql="Update Teacher SET "
				+ "first_name=?, "
				+ "last_name=?, "
				+ "designation=? "
				+ "where id=?";
		try(Connection con=
				DatabaseConnectionFactory.getConnectionFactory()
				.getConnection();
			PreparedStatement preparedStatement=
				con.prepareStatement(sql)){
			preparedStatement.setString(1, teacher.getFirstName());
			preparedStatement.setString(2, teacher.getLastName());
			preparedStatement.setString(3, teacher.getDesignation());
			preparedStatement.setInt(4, teacher.getId());
			preparedStatement.executeUpdate();
			
		}
		
	}
	public List<Teacher> getTeachers () throws SQLException {
		//get connection from connection pool
		Connection con = DatabaseConnectionFactory.getConnectionFactory().getConnection();

		List<Teacher> teachers = new ArrayList<Teacher>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			
			String sql = "select * from Teacher ";
			
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(rs.getInt("id"));
				teacher.setFirstName(rs.getString("first_name"));
				teacher.setLastName(rs.getString("last_name"));
				teacher.setDesignation(rs.getString("designation"));
				teachers.add(teacher);
			}
			stmt.close();
			rs.close();
			
		} finally {
			con.close();
		}
		return teachers;
	}

	public Teacher getTeacher(int id) throws SQLException {
		Connection con = DatabaseConnectionFactory.getConnectionFactory().getConnection();
		String sql = "select * from Teacher where id=?";
		Teacher teacher =null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			
			
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				 teacher = new Teacher();
				teacher.setId(rs.getInt("id"));
				teacher.setFirstName(rs.getString("first_name"));
				teacher.setLastName(rs.getString("last_name"));
				teacher.setDesignation(rs.getString("designation"));
				
			}
			rs.close();
			preparedStatement.close();
		} finally {
			con.close();
		}
		return teacher;
	}

}
