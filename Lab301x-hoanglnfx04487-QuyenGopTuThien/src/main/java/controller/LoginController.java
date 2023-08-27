package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PersonDAO;
import model.PersonModel;
import util.PasswordUtil;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        
        PrintWriter out = response.getWriter();
        
        // check if user logged in
		HttpSession session = request.getSession(true);
		PersonModel person = (PersonModel) session.getAttribute("person");		
		if (person != null) { 
			response.sendRedirect("index.jsp");
			return;
		}
        
		// if user doesn't log in, execute log in progress
        try {
			String email_input = request.getParameter("email");
			String password_input = request.getParameter("password");
			
			PersonDAO dao = new PersonDAO();
			
			if (dao.isPersonExisted(email_input)) {
				PersonModel personCheck = dao.getPerson(email_input, new PasswordUtil().hashPasswordMD5(password_input));

				// check password
				if (personCheck != null) {       
                    // check role
					if (personCheck.getRole().equalsIgnoreCase(request.getParameter("userType"))) {	
						// check banned
						if (personCheck.getStatus().equalsIgnoreCase("Active")) {
	    					// set data to session scope => loged in
	    					session.setAttribute("person", personCheck);
	    					session.setAttribute("emailOTP", personCheck.getEmail());
	    					
	    					// add to cookie
	    					Cookie cEmail = new Cookie("email_person", personCheck.getEmail());             
	    					cEmail.setMaxAge(60*60*24); // 24h * 60m * 60s => (s)   
	                        response.addCookie(cEmail);

	                        dao.updateLastLogin(personCheck.getEmail());
							// redirect to to home page
	                        response.sendRedirect("index.jsp");
						} else {
							out.println("<script type=\"text/javascript\">");
							out.println("alert('Your account has been Banned !! !!');");
							out.println("window.location.replace('page-user-login.jsp');");
							out.println("</script>");
						}
					} else {
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Wrong role !! !!');");
						out.println("window.location.replace('page-user-login.jsp');");
						out.println("</script>");
					}
                    
					
				} else {
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Wrong password !!');");
					out.println("window.location.replace('page-user-login.jsp');");
					out.println("</script>");
//					request.getRequestDispatcher("page-user-login.jsp").forward(request, response);
				}
			}  else {
				out.println("<script type=\"text/javascript\">");
				out.println("var confirmation = confirm('Account is Not Registered !! Do you want to proceed to registration?');");
				out.println("if (confirmation) {");
				out.println("  window.location.replace('page-user-registry.jsp');");
				out.println("} else {");
				out.println("  window.location.replace('page-user-login.jsp');");
				out.println("}");
				out.println("</script>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}