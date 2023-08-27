package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CampaignDAO;
import dao.ContributionRecordDAO;
import model.CampaignModel;
import model.ContributionRecordModel;

/**
 * Servlet implementation class CampaignDetailController
 */
@WebServlet("/CampaignDetailController")
public class CampaignDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CampaignDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int campaignId = Integer.parseInt(request.getParameter("campaignId"));
		
		CampaignDAO campaignDAO = new CampaignDAO();
		ContributionRecordDAO contributionRecordDAO = new ContributionRecordDAO();
		
		CampaignModel campaign = campaignDAO.getCampaignById(campaignId);
		List<ContributionRecordModel> contributionRecords = contributionRecordDAO.getAllContributionsByCampaignId(campaignId);
		
		
		request.setAttribute("campaign", campaign);
		request.setAttribute("contributionRecords", contributionRecords);
		
		request.getRequestDispatcher("page-campaign-detail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
