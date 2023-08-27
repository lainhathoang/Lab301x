package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContributionRecordDAO;
import model.ContributionRecordModel;

/**
 * Servlet implementation class ContributionFormController
 */
@WebServlet("/ContributionFormController")
public class ContributionFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContributionFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ContributionRecordDAO contributionRecordDAO = new ContributionRecordDAO();
		
		int campaignId = Integer.parseInt(request.getParameter("campaignId"));
		String email = request.getParameter("email");
		String contributionMethod = request.getParameter("contributionMethod");
		String donationTime = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		double donateAmount = Double.parseDouble(request.getParameter("donateAmount"));
		
		ContributionRecordModel contributionRecordModel = new ContributionRecordModel(campaignId, email, donationTime, donateAmount,contributionMethod);
	
		contributionRecordDAO.addContribution(contributionRecordModel);
		
		response.sendRedirect("ContributionRecordsController?campaignId=" + campaignId);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
