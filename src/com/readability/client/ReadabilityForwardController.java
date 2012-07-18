package com.readability.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author yongboy
 * 
 */
@WebServlet("/readability/*")
public class ReadabilityForwardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute(ReadabilityConstants.PREFIX) == null) {
			response.sendRedirect("../readability");
			return;
		}

		String relativeUrl = request.getRequestURI().substring(
				request.getContextPath().length());

		String jspFileName = relativeUrl.substring("/readability/".length());
		request.getRequestDispatcher("/WEB-INF/jsp/" + jspFileName + ".jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}