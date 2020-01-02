package com.lim.portfolio.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lim.portfolio.model.dto.Portfolio;
import com.lim.portfolio.model.dto.PortfolioData;
import com.lim.portfolio.model.service.PortfolioService;
import com.lim.portfolio.util.DateTimeFileReNamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class PortfolioRegisterServlet
 */
@WebServlet("/portfolio_register.do")
public class PortfolioRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			String saveDir = "uploadFileSave";
			String saveFullDir = getServletContext().getRealPath(saveDir);
			int maxFileSize = 5*1024*1024;
			//int maxFileSize = 5*1024;
			String encoding = "utf-8";
			
			MultipartRequest mRequest = null;
		 	mRequest = new MultipartRequest(request,saveFullDir,maxFileSize,encoding,
		 			new DateTimeFileReNamePolicy());
		 	
		 	String title = mRequest.getParameter("title");
		 	String leader = mRequest.getParameter("leader");
		 	String member = mRequest.getParameter("member");
		 	String content = mRequest.getParameter("content"); 
		 	String startDate = mRequest.getParameter("startDate");
		 	String endDate = mRequest.getParameter("endDate"); 
		 	
		 	Portfolio p = new Portfolio(title, leader, member, content, startDate, endDate);

		 	ArrayList<PortfolioData> list = new ArrayList<PortfolioData>();
		 	Enumeration em = mRequest.getFileNames();
		 	while(em.hasMoreElements()) {
		 		String name = (String)em.nextElement();
		 		String originalFileName = mRequest.getOriginalFileName(name);
		 		// 4개 다 들어와 버리니까 파일이름이 없을 때 넘어가버리는 코드
		 		if(originalFileName == null) continue;
		 		String realFileName = mRequest.getFilesystemName(name);
		 		list.add(new PortfolioData(originalFileName, realFileName));
		 	}
		 	
		 	p.setDataList(list);
		 	
		 	PortfolioService pService = new PortfolioService();
		 	pService.register(p);
		 	
		 	response.sendRedirect("portfolio_list.do");
	 		
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}		 		
	} // end doPost   	  	    
} // end class
