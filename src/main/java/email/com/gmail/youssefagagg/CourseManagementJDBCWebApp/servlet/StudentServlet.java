package email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean.Course;
import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean.Student;
import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.dao.StudentDAO;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/studentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
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
		case "addStudent":
			addOrUpdateStudent(req,resp,false);

			break;
		case "updateStudent":
			addOrUpdateStudent(req,resp,true);

			break;
		case "updateFromListStudent": 
			updateFromListStudent(req,resp);
			break;
		case "deleteFromListStudent": 
			deleteFromListStudent(req,resp);
			break;
		}
	}
	private void addOrUpdateStudent(HttpServletRequest req, HttpServletResponse resp,boolean update) throws ServletException, IOException {
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
	
			try {
				if(!update) {
				student.addStudent();
				}else {
					
					int id=Integer.parseInt(req.getParameter("studentid"));
					student.setId(id);
					student.updateStudent();

				}
			} catch (SQLException e) {
				req.setAttribute("message", e.getMessage());
				if(!update)
					url="/addStudent.jsp";
				else 
					url="/updateStudent.jsp";
				e.printStackTrace();
			}
		
		req.getRequestDispatcher(url)
		.forward(req, resp);

	}
	private void updateFromListStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StudentDAO studentDAO=new StudentDAO();
		int id=Integer.parseInt(req.getParameter("studentid"));
		try {
			Student student=studentDAO.getStudent(id);
			req.setAttribute("student", student);
			req.getServletContext().getRequestDispatcher("/updateStudent.jsp")
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
		req.getServletContext().getRequestDispatcher(url)
		.forward(req, resp);

	}


}
