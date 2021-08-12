package email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean.Course;
import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean.Student;
import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean.Teacher;
import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.dao.CourseDAO;
import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.dao.StudentDAO;
import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.dao.TeacherDAO;
import email.com.gmail.youssefagagg.db.connection.DatabaseConnectionFactory;

/**
 * Servlet implementation class InitServlet
 */
@WebServlet(value = "/initServlet",loadOnStartup = 1)
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			DatabaseConnectionFactory.getConnectionFactory().init();
		}
		catch (IOException e) {
			config.getServletContext().log(e.getLocalizedMessage(),e);
		}


	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/index.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url="/index.html";
		String action =req.getParameter("action");
		if(action==null) {
			req.getSession().getServletContext().getRequestDispatcher(url)
			.forward(req, resp);
			return;
		}

		switch (action) {
		case "addCourse":
			addCourse(req,resp,false);

			break;
		case "updateCourse":
			addCourse(req,resp,true);

			break;
		case "updateFromListCourse": 
			updateFromListCourse(req,resp);
			break;
		case "deleteFromListCourse": 
			deleteFromListCourse(req,resp);
			break;
		case "addStudent":
			addStudent(req,resp,false);

			break;
		case "updateStudent":
			addStudent(req,resp,true);

			break;
		case "updateFromListStudent": 
			updateFromListStudent(req,resp);
			break;
		case "deleteFromListStudent": 
			deleteFromListStudent(req,resp);
			break;
		case "addTeacher":
			addTeacher(req,resp,false);

			break;
		case "updateTeacher":
			addTeacher(req,resp,true);

			break;
		case "updateFromListTeacher": 
			updateFromListTeacher(req,resp);
			break;
		case "deleteFromListTeacher": 
			deleteFromListTeacher(req,resp);
			break;

		default:
			req.getRequestDispatcher(url)
			.forward(req, resp);
			break;
		}
	}

	private void deleteFromListTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url="/listTeacher.jsp";
		int id=Integer.parseInt(req.getParameter("teacherid"));
		TeacherDAO teacherDAO=new TeacherDAO();
		try {
			teacherDAO.deleteTeacher(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getSession().getServletContext().getRequestDispatcher(url)
		.forward(req, resp);

	}

	private void updateFromListTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TeacherDAO teacherDAO=new TeacherDAO();
		int id=Integer.parseInt(req.getParameter("teacherid"));
		try {
			Teacher teacher=teacherDAO.getTeacher(id);
			req.getSession().setAttribute("teacher", teacher);
			req.getSession().getServletContext().getRequestDispatcher("/addTeacher.jsp")
			.forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteFromListStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url="/listStudent.jsp";
		int id=Integer.parseInt(req.getParameter("studentid"));
		StudentDAO studentDAO=new StudentDAO();
		try {
			studentDAO.deleteStudent(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getSession().getServletContext().getRequestDispatcher(url)
		.forward(req, resp);

	}

	private void updateFromListStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StudentDAO studentDAO=new StudentDAO();
		int id=Integer.parseInt(req.getParameter("studentid"));
		try {
			Student student=studentDAO.getStudent(id);
			req.getSession().setAttribute("student", student);
			req.getSession().getServletContext().getRequestDispatcher("/addStudent.jsp")
			.forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void deleteFromListCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url="/listCourse.jsp";
		int id=Integer.parseInt(req.getParameter("courseid"));
		CourseDAO courseDAO=new CourseDAO();
		try {
			courseDAO.deleteCourse(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getSession().getServletContext().getRequestDispatcher(url)
		.forward(req, resp);

	}

	private void updateFromListCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CourseDAO courseDAO=new CourseDAO();
		int id=Integer.parseInt(req.getParameter("courseid"));
		try {
			Course course=courseDAO.getCourse(id);
			req.getSession().setAttribute("course", course);
			req.getSession().getServletContext().getRequestDispatcher("/addCourse.jsp")
			.forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addTeacher(HttpServletRequest req, HttpServletResponse resp,boolean update) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url="/listTeacher.jsp";
		Teacher teacher=new Teacher();
		String firstName=req.getParameter("firstName");
		String lastName=req.getParameter("lastName");
		String designation=req.getParameter("designation");
		teacher.setFirstName(firstName);
		teacher.setLastName(lastName);
		teacher.setDesignation(designation);
		boolean vaild=vaildTeacher(firstName,lastName,designation);
		if(vaild) {
			try {
				if(!update) {
				teacher.addTeacher();
				}else {
					int id=Integer.parseInt(req.getParameter("teacherid"));
					teacher.setId(id);
					teacher.updateTeacher();
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			if(update)req.setAttribute("teacher", teacher);
			req.setAttribute("message", "enter vaild data");
			url="/addTeacher.jsp";
		}
		
		
		req.getRequestDispatcher(url)
		.forward(req, resp);;
		

	}

	private boolean vaildTeacher(String firstName, String lastName, String designation) {
		// TODO Auto-generated method stub
		if(!vaildString(firstName))return false;
		if(!vaildString(lastName))return false;
		if(!vaildString(designation))return false;
		return true;
	}

	private boolean vaildString(String s) {
		// TODO Auto-generated method stub
		return s!=null&&!s.isEmpty() ;
	}

	private void addStudent(HttpServletRequest req, HttpServletResponse resp,boolean update) throws ServletException, IOException {
		String url="/listStudent.jsp";
		Student student=new Student();
		String firstName=req.getParameter("firstName");
		String lastName=req.getParameter("lastName");
		String[] coursesids=req.getParameterValues("coursesids");
		
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEnrolledsince(new Date().getTime());
		List<Course>courses=new ArrayList<Course>();
		if(coursesids!=null) {
			for(String corseid:coursesids) {
				Course course=new Course();
				course.setId(Integer.parseInt(corseid));
				courses.add(course);

			}
		}
		student.setCourses(courses);
		if(vaildString(firstName)&&vaildString(lastName)) {
			try {
				if(!update) {
				student.addStudent();
				}else {
					
					int id=Integer.parseInt(req.getParameter("studentid"));
					student.setId(id);
					student.updateStudent();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			if(update)					
				req.setAttribute("student", student);
			
			req.setAttribute("message", "enter vaild data");
			url="/addStudent.jsp";
		}
		req.getRequestDispatcher(url)
		.forward(req, resp);

	}

	private void addCourse(HttpServletRequest req, HttpServletResponse resp,boolean update) throws ServletException, IOException {
		String url="/listCourse.jsp";
		Course course=new Course();
		String name=req.getParameter("name");
		String credites=req.getParameter("credits");
		String teacherID=req.getParameter("teacherid");


		if(vaildString(teacherID)) {
			Teacher teacher=new Teacher();
			teacher.setId(Integer.parseInt(teacherID));
			course.setTeacher(teacher);
		}
		if(vaildInt(credites))
			course.setCredits(Integer.parseInt(credites));

		course.setName(name);
		boolean vaild= vaildCourse(name,credites);
		if(vaild) {

			try {
				if(!update) {
					course.addCourse();
				}else {
					int id=Integer.parseInt(req.getParameter("courseid"));
					course.setId(id);
					course.updateCourse();
					

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			if(update) req.setAttribute("course", course);
			req.setAttribute("message", "enter vaild data");
			url="/addCourse.jsp";

		}




		req.getRequestDispatcher(url)
		.forward(req, resp);

	}

	private boolean vaildCourse(String name, String credites) {

		if(!vaildString(name)||!vaildString(credites))return false;

		return vaildInt(credites);

	}

	private boolean vaildInt(String credites) {
		try {
			int c=Integer.parseInt(credites);
			if(c<=0) return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
