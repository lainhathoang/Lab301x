package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PersonDAO;
import model.PersonModel;

/**
 * Servlet implementation class UserSearchController
 */
@WebServlet("/UserSearchController")
public class UserSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserSearchController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		
		// declare page 
		int page = 1;
		int recordsPerPage = 20;	
		
		PersonDAO dao = new PersonDAO();
		String search = request.getParameter("search");
		
		List<PersonModel> list = new ArrayList<PersonModel>();
		
        // kiem tra param "page" xem dang o trang so may
        if (request.getParameter("page") != null) {
        	try {
        		page = Integer.parseInt(request.getParameter("page"));
        	} catch (Exception e) {
        		request.getRequestDispatcher("page-404.jsp").forward(request, response);
        		return;
        	}               
        }
		
        
        // goi ham query Search de lay ra du lieu tu db 
        // & luu vao "list"
        list = dao.querySearch((page - 1) * recordsPerPage, recordsPerPage, search);
        
        // khai bao so record & so pages lay duoc 
        int numOfRecords = dao.getNumOfRecords();     
        int numOfPages = (int) Math.ceil(numOfRecords * 1.0 / recordsPerPage);
        
        request.setAttribute("list", list);
        request.setAttribute("numPage", numOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("search", search);
        
        request.getRequestDispatcher("page-user-manager.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
