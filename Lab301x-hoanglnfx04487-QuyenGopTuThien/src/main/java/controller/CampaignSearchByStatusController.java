package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CampaignDAO;
import model.CampaignModel;

/**
 * Servlet implementation class CampaignSearchByStatusController
 */
@WebServlet("/CampaignSearchByStatusController")
public class CampaignSearchByStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CampaignSearchByStatusController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CampaignDAO dao = new CampaignDAO();
		List<CampaignModel> campaigns = dao.findCampaignsByStatus(request.getParameter("status"));
		
		request.setAttribute("campaigns", campaigns);
		
		request.getRequestDispatcher("page-campaign-list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
