package com.lim.portfolio.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lim.portfolio.model.service.PortfolioService;

@WebServlet("/portfolio_ajax_detail.do")
public class PortfolieAjaxDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json;charset=utf-8");
		// parameter : no
		int no = Integer.parseInt(request.getParameter("no"));
		HashMap<String,Object> map = new HashMap<String,Object>();
		Gson gson =  new Gson();

		PortfolioService pService = new PortfolioService();
		try {
			map.put("result", true);			
			map.put("portfolio",pService.getDetail(no) );
		} catch (Exception e) {
			map.put("result", false);
			e.printStackTrace();
		}
		response.getWriter().println(gson.toJson(map));
	}

}
