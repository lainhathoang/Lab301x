	package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CampaignDAO;
import model.CampaignModel;
import service.DBService;

/**
 * Servlet implementation class CampaignListController
 */
@WebServlet("/CampaignListController")
public class CampaignListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CampaignListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		List<CampaignModel> campaigns = new ArrayList<CampaignModel>();
		
		try {
            // Connect to the database
            Connection connection = new DBService().getConnection();

            // Create a CampaignDAO object
            CampaignDAO campaignDAO = new CampaignDAO();

            // Get campaigns from the database       
        	campaigns = campaignDAO.findCampaigns(request.getParameter("search"));	
            

            // Close the database connection
            connection.close();

            // Set the campaigns as a request attribute
            session.setAttribute("campaigns", campaigns);

            // Forward the request to the campaign list JSP
            request.getRequestDispatcher("page-campaign-list.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // You can handle database connection errors here or redirect to an error page
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
