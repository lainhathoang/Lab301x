package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContributionRecordDAO;
import model.ContributionRecordModel;

/**
 * Servlet implementation class ContributionRecordsController
 */
@WebServlet("/ContributionRecordsController")
public class ContributionRecordsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContributionRecordsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ContributionRecordDAO dao = new ContributionRecordDAO();

		// from the donation form
		if (request.getParameter("campaignId") != null) {
			int campaignId = Integer.parseInt(request.getParameter("campaignId").toString());

			List<ContributionRecordModel> contributionRecords = new ArrayList<ContributionRecordModel>();

			contributionRecords = dao.getAllContributionsByCampaignId(campaignId);
			String campaignName = dao.getCampaignNameByContributionId(campaignId);

			request.setAttribute("contributionRecords", contributionRecords);
			request.setAttribute("campaignName", campaignName);
		}

		// from the sidebar
		if (request.getParameter("email") != null) {
			String email = request.getParameter("email").toString();

			List<ContributionRecordModel> contributionRecords = new ArrayList<ContributionRecordModel>();
			contributionRecords = dao.getRecordsByEmail(email);
			
			System.out.println(contributionRecords);
			
			request.setAttribute("contributionRecords", contributionRecords);
			request.setAttribute("email", email);
		}

		request.getRequestDispatcher("page-contribution-records.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
