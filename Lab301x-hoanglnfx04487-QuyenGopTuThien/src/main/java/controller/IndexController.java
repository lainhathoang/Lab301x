package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PersonModel;

/**
 * Servlet implementation class IndexController
 */
@WebServlet("/IndexController")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession(true);

		PersonModel person = (PersonModel) session.getAttribute("person");

		if (person != null) {
			if (person.getRole().equalsIgnoreCase("Admin")) {
				response.sendRedirect("UserManagerController");
			}
			if (person.getRole().equalsIgnoreCase("User")) {
				response.sendRedirect("CampaignListController?search=");
			}
		} else {
			response.sendRedirect("CampaignListController?search=");
		}
	}

}
