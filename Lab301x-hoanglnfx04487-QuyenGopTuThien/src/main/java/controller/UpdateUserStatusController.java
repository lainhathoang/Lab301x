package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PersonDAO;

/**
 * Servlet implementation class DeleteUserController
 */
@WebServlet("/UpdateUserStatusController")
public class UpdateUserStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUserStatusController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] toDelete = request.getParameterValues("toBeChanged[]");
		String type = request.getParameter("type");

		int[] toBeDeleted = new int[toDelete.length];

		for (int i = 0; i < toDelete.length; i++) {
			toBeDeleted[i] = Integer.parseInt(toDelete[i]);
		}

		PersonDAO dao = new PersonDAO();

		if (type.equalsIgnoreCase("ban")) {
			dao.banUsers(toBeDeleted);
		}

		if (type.equalsIgnoreCase("active")) {
			dao.activeUser(toBeDeleted);
		}

		if (type.equalsIgnoreCase("delete")) {
			dao.deleteUser(toBeDeleted);
		}

	}

}
