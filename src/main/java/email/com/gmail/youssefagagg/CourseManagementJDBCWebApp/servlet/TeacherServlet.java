package email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.bean.Teacher;
import email.com.gmail.youssefagagg.CourseManagementJDBCWebApp.dao.TeacherDAO;

/**
 * Servlet implementation class TeacherServlet
 */
@WebServlet("/teacherServlet")
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherDAO teacherDAO;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		teacherDAO=new TeacherDAO();
		super.init();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherServlet() {
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
		case "addTeacher":
			addorUpdteTeacher(req,resp,false);

			break;
		case "updateTeacher":
			addorUpdteTeacher(req,resp,true);

			break;
		case "updateFromListTeacher": 
			updateFromListTeacher(req,resp);
			break;
		case "deleteFromListTeacher": 
			deleteFromListTeacher(req,resp);
			break;
		}

	}
	private void addorUpdteTeacher(HttpServletRequest req, HttpServletResponse resp,boolean update) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url="/listTeacher.jsp";
		Teacher teacher=new Teacher();
		String firstName=req.getParameter("firstName");
		String lastName=req.getParameter("lastName");
		String designation=req.getParameter("designation");
		teacher.setFirstName(firstName);
		teacher.setLastName(lastName);
		teacher.setDesignation(designation);
		
			try {
				if(!update) {
				teacherDAO.addTeacher(teacher);
				}else {
					int id=Integer.parseInt(req.getParameter("teacherid"));
					teacher.setId(id);
					teacherDAO.updateTeacher(teacher);
					
				}
			} catch (SQLException e) {
				req.setAttribute("message", e.getMessage());
				if(!update)
					url="/addTeacher.jsp";
				else 
					url="/updateTeacher.jsp";
				e.printStackTrace();
			}
		
		
		
		req.getRequestDispatcher(url)
		.forward(req, resp);;
		

	}
	private void updateFromListTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		int id=Integer.parseInt(req.getParameter("teacherid"));
		try {
			Teacher teacher=teacherDAO.getTeacher(id);
			req.setAttribute("teacher", teacher);
			req.getServletContext().getRequestDispatcher("/updateTeacher.jsp")
			.forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void deleteFromListTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url="/listTeacher.jsp";
		int id=Integer.parseInt(req.getParameter("teacherid"));
		
		try {
			teacherDAO.deleteTeacher(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getServletContext().getRequestDispatcher(url)
		.forward(req, resp);

	}


}
