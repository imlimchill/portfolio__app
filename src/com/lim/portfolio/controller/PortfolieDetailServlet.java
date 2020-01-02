package com.lim.portfolio.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lim.portfolio.model.service.PortfolioService;

@WebServlet("/portfolio_detail.do")
public class PortfolieDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// parameter : no
		int no = Integer.parseInt(request.getParameter("no"));

		PortfolioService pService = new PortfolioService();
		try {
			request.setAttribute("portfolio", pService.getDetail(no));
			request.getRequestDispatcher("portfolio_detail.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
		}
	}

}
