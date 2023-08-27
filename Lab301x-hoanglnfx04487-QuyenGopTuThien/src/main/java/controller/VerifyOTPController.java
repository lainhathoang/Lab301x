package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class VerifyOTPController
 */
@WebServlet("/VerifyOTPController")
public class VerifyOTPController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerifyOTPController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession(true);
		
		int codeOTP = -1;

		try {
			codeOTP = (int) session.getAttribute("codeOTP");
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('OTP is invalid !! !!');");
			out.println("window.location.replace('page-user-forgot-password.jsp');");
			out.println("</script>");
		}
		
		int userOTP = Integer.parseInt(request.getParameter("userOTP"));

		if (codeOTP == userOTP) {
			session.removeAttribute("codeOTP");
			response.sendRedirect("page-user-change-password.jsp");
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Wrong OTP code !! !!');");
			out.println("window.location.replace('page-verify-otp.jsp');");
			out.println("</script>");
		}
	}

}
