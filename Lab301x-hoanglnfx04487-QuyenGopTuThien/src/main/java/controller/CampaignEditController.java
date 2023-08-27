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
 * Servlet implementation class CampaignEditController
 */
@WebServlet("/CampaignEditController")
public class CampaignEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CampaignEditController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Editting Id: ").append(request.getParameter("id").toString());
//		request.setAttribute("id", request.getParameter("id"));
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		CampaignDAO dao = new CampaignDAO();
		CampaignModel campaign = dao.getCampaignById(Integer.parseInt(request.getParameter("id")));
		
		request.setAttribute("campaign", campaign);
		request.setAttribute("id", id);
		
		request.getRequestDispatcher("page-campaign-edit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
