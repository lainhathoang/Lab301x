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
import service.MailService;
import util.GetOTPUtil;

/**
 * Servlet implementation class ForgotPasswordServlet
 */
@WebServlet("/ForgotPasswordController")
public class ForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPasswordController() {
        super();
       
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        
        int otp = new GetOTPUtil().createOTP();
        String email = request.getParameter("email");
        
        PrintWriter out = response.getWriter();
        
      
        
        
        try {
        	// store email to session data
        	HttpSession session = request.getSession(true);
        	session.setAttribute("emailOTP", email);
        	session.setAttribute("codeOTP", otp);
        	
			PersonDAO dao = new PersonDAO();
			
			// check if email is existed in DB
			if (dao.isPersonExisted(email)) {
				// send email 
				new MailService().sendEmailOTP(email, otp);
				
				response.sendRedirect("page-verify-otp.jsp");

			} else {
		        out.println("<html>");
		        out.println("User: " + email + "doesn't exist");
		        out.println("<a href='page-user-login.jsp'>Log In </a>");
		        out.println("</html>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
//        out.println("<html>");
//        out.println("Email sent to: " + request.getParameter("email") + "otp: " + otp);
//        out.println("<a href='page-user-login.jsp'>Log In </a>");
//        out.println("</html>");
       
	}

}
