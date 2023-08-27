package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CampaignDAO;
import dao.ContributionRecordDAO;
import model.ContributionRecordModel;

/**
 * Servlet implementation class ContributionActionController
 */
@WebServlet("/ContributionActionController")
public class ContributionActionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContributionActionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int verify = Integer.parseInt(request.getParameter("verify"));
		int contributionId = Integer.parseInt(request.getParameter("contributionId"));
		int campaignId = Integer.parseInt(request.getParameter("campaignId"));

		System.out.println(verify + " - " + contributionId + " - " + campaignId);

		ContributionRecordDAO contributionRecordDAO = new ContributionRecordDAO();
		CampaignDAO camDAO = new CampaignDAO();
		
		ContributionRecordModel record = contributionRecordDAO.getContributionById(contributionId);

		// kiem tra de cong tien hoac tru tien
		if (verify == 0) {
			contributionRecordDAO.setContributionVerifyStatus(contributionId, 0);
			camDAO.updateCurrentAmount(campaignId, record.getContributionAmount(), 0);		
		} else {
			contributionRecordDAO.setContributionVerifyStatus(contributionId, 1);
			camDAO.updateCurrentAmount(campaignId, record.getContributionAmount(), 1);
		}

		response.sendRedirect("CampaignDetailController?campaignId=" + campaignId);
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
