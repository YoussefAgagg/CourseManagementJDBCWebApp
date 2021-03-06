package email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.db.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class DatabaseConnectionFactory {
	private static DatabaseConnectionFactory conFactory = new
			DatabaseConnectionFactory();
	private DataSource dataSource = null;
	private DatabaseConnectionFactory() {}

	public synchronized void init()throws IOException{
		if(dataSource!=null) return;
		InputStream inputStream=
				getClass().getClassLoader().getResourceAsStream("db.properties");
		Properties dbProperties=new Properties();
		dbProperties.load(inputStream);
		inputStream.close();
		PoolProperties poolProperties=new PoolProperties();
		poolProperties.setUrl("jdbc:mysql://" + dbProperties.getProperty("db_host") +
				":" + dbProperties.getProperty("db_port") + "/" +
				dbProperties.getProperty("db_name"));
		poolProperties.setDriverClassName(dbProperties.getProperty("db_driver_class_name"));
		poolProperties.setUsername(dbProperties.getProperty("db_user_name"));
		poolProperties.setPassword(dbProperties.getProperty("db_password"));
		poolProperties.setMaxActive(10);
		poolProperties.setMaxIdle(10);
		poolProperties.setMaxWait(1000);
		
		dataSource = new DataSource();
		dataSource.setPoolProperties(poolProperties);

	}
	public static DatabaseConnectionFactory getConnectionFactory() {
		return conFactory;
	}
	public Connection getConnection () throws SQLException {
		if (dataSource == null)
			throw new SQLException("Error initializing datasource");
		return dataSource.getConnection();
	}


}
