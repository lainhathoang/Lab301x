package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        
        try {
        	
            HttpSession session = request.getSession(true);  
            
            // remove "person" attribute -> loged out
            session.removeAttribute("person");
            
            session.removeAttribute("emailOTP");
            session.removeAttribute("codeOTP");
            
            response.sendRedirect("index.jsp");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
