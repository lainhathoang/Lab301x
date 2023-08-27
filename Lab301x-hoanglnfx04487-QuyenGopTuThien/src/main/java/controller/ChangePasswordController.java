package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PersonDAO;

/**
 * Servlet implementation class ChangePasswordController
 */
@WebServlet("/ChangePasswordController")
public class ChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        
        HttpSession session = request.getSession(true);
        PrintWriter out = response.getWriter();
        
        try {
			PersonDAO dao = new PersonDAO();
			dao.updatePassword(session.getAttribute("emailOTP").toString(), request.getParameter("password"));
			
			
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Password has been Changed, Please login again !!');");
			out.println("window.location.replace('LogoutController');");
			out.println("</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
        
//        System.out.println("Email: " + session.getAttribute("emailOTP"));
//        System.out.println("New pwd: " + request.getParameter("password"));
	}

}
