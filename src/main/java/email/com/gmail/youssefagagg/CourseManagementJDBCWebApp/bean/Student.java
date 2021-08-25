package email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean;

import java.util.List;


public class Student extends Person {
	private long enrolledsince;
	private List<Course>courses;

	public long getEnrolledsince() {
		return enrolledsince;
	}
	public void setEnrolledsince(long enrolledsince) {
		this.enrolledsince = enrolledsince;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
}
