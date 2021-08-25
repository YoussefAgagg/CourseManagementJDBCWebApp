package email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean.Course;
import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean.Teacher;
import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.dao.CourseDAO;

/**
 * Servlet implementation class CourseServlet
 */
@WebServlet("/courseServlet")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourseDAO courseDAO;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		courseDAO=new CourseDAO();
		super.init();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CourseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
			addOrUpdateCourse(req,resp,false);

			break;
		case "updateCourse":
			addOrUpdateCourse(req,resp,true);

			break;
		case "updateFromListCourse": 
			updateFromListCourse(req,resp);
			break;
		case "deleteFromListCourse": 
			deleteFromListCourse(req,resp);
			break;
		}
	}
	private void addOrUpdateCourse(HttpServletRequest req, HttpServletResponse resp,boolean update) throws ServletException, IOException {
		String url="/listCourse.jsp";
		Course course=new Course();
		String name=req.getParameter("name");
		String credites=req.getParameter("credits");
		String teacherID=req.getParameter("teacherid");


		if(teacherID !=null&&!teacherID.isEmpty()) {
			Teacher teacher=new Teacher();
			teacher.setId(Integer.parseInt(teacherID));
			course.setTeacher(teacher);
		}

		course.setCredits(Integer.parseInt(credites));

		course.setName(name);



		try {
			if(!update)
				courseDAO.addCourse(course);
			else {
				int id=Integer.parseInt(req.getParameter("courseid"));
				course.setId(id);
				courseDAO.updateCorse(course);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			req.setAttribute("message", e.getMessage());
			if(!update)
				url="/addCourse.jsp";
			else 
				url="/updateCourse.jsp";

			e.printStackTrace();
		}





		req.getRequestDispatcher(url)
		.forward(req, resp);

	}
	private void updateFromListCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id=Integer.parseInt(req.getParameter("courseid"));
		try {
			Course course=courseDAO.getCourse(id);
			req.setAttribute("course", course);
			req.getServletContext().getRequestDispatcher("/updateCourse.jsp")
			.forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private void deleteFromListCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url="/listCourse.jsp";
		int id=Integer.parseInt(req.getParameter("courseid"));
		
		try {
			courseDAO.deleteCourse(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getSession().getServletContext().getRequestDispatcher(url)
		.forward(req, resp);

	}

}
