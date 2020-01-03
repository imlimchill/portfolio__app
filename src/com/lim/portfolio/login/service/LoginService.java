package com.lim.portfolio.login.service;

import java.util.ArrayList;

import com.lim.portfolio.login.dao.LoginDAO;
import com.lim.portfolio.login.dto.LoginInfo;
import com.lim.portfolio.login.dto.UserInfo;



public class LoginService {
	
	private LoginDAO lDAO = new LoginDAO();
	
	public void register(LoginInfo l) throws Exception {
		// insert가 되면 포트폴리오 번호가 리턴된다.
		int no = lDAO.insert(l);
		
		lDAO.insert(no, l.getUser());
		
	}
	
	public void loginUser(String id) {
		
	}
}
