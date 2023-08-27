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
import model.PersonModel;

/**
 * Servlet implementation class RegistryController
 */
@WebServlet("/RegistryController")
public class RegistryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistryController() {
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
        
        try {
        	// check if the browser stored an account in cookie session
			HttpSession session = request.getSession(true);
			PersonModel person = (PersonModel) session.getAttribute("person");
			
			
			// get data from form in to the new Person Model
			if (person == null) person = new PersonModel();
			person.setEmail(request.getParameter("email"));
			person.setPhone(Integer.parseInt(request.getParameter("phoneNumber")));
			person.setPassword(request.getParameter("password"));
		
			// use Person DAO to store data into database
			PersonDAO dao = new PersonDAO();
			
			if (!dao.isPersonExisted(request.getParameter("email"))) {
				dao.createPerson(person);								

				out.println("<script type=\"text/javascript\">");
				out.println("alert('Account is Created !!');");
				out.println("window.location.replace('page-user-login.jsp');");
				out.println("</script>");
				
			} else {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Account is Existed !!');");
				out.println("window.location.replace('page-user-registry.jsp');");
				out.println("</script>");
				
//				RequestDispatcher rd = request.getRequestDispatcher("page-user-registry.jsp");
//				rd.include(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
