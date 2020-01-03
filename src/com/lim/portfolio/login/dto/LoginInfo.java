

package com.lim.portfolio.login.dto;

public class LoginInfo {
	private int userSeq;
	private String userId;
	private String userPassword;
	private String userEmail;
	private String registerDate;
	
	private UserInfo user;
	
	public LoginInfo(String userId, String userPassword, String userEmail, String registerDate) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.registerDate = registerDate;
	}

	public LoginInfo(int userSeq, String userId, String userPassword) {
		super();
		this.userSeq = userSeq;
		this.userId = userId;
		this.userPassword = userPassword;
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}
	
	
}
