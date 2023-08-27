package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CampaignDAO;
import model.CampaignModel;

/**
 * Servlet implementation class CreateCampaignController
 */
@WebServlet("/CreateCampaignController")
public class CampaignCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CampaignCreateController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Retrieve data from the form
		String owner = request.getParameter("owner");
		String email = request.getParameter("email");
		long phone = Long.parseLong(request.getParameter("phone"));
		String bankName = request.getParameter("bankName");
		long bankAccount = Long.parseLong(request.getParameter("bankAccount"));
		String title = request.getParameter("title");
		String status = request.getParameter("status");
		String description = request.getParameter("description");
		String targetAmount = request.getParameter("targetAmount");
		String currentAmount = request.getParameter("currentAmount");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		// Create a Campaign object with the form data
		CampaignModel campaign = new CampaignModel();
		campaign.setOwner(owner);
		campaign.setEmail(email);
		campaign.setPhone(phone);
		campaign.setBankName(bankName);
		campaign.setBankAccount(bankAccount);
		campaign.setTitle(title);
		campaign.setStatus(status);
		campaign.setDescription(description);
		campaign.setTargetAmount(Double.parseDouble(targetAmount));
		campaign.setCurrentAmount(Double.parseDouble(currentAmount));
		campaign.setStartDate(startDate);
		campaign.setEndDate(endDate);

		// Create a CampaignDAO object and insert the campaign into the database
		try {
			CampaignDAO campaignDAO = new CampaignDAO(); // Provide your database connection method
			campaignDAO.insertCampaign(campaign);
			// You may also redirect to a success page or display a success message here
			response.sendRedirect("CampaignListController?search=");
		} catch (Exception e) {
			// Handle any exceptions that might occur during database insertion
			e.printStackTrace();
			// You may redirect to an error page or display an error message here
			response.sendRedirect("page-404.jsp");
		}
	}

}
