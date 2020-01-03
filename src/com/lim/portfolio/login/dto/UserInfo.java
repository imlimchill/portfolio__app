package com.lim.portfolio.login.dto;

public class UserInfo {
	private int no;
	private String userName;
	private String englishName;
	private String userTel;
	private String firshMajor;
	private String userEmail;
	private String secondMajor;
	private String profilePicture;
	
	public UserInfo(int no, String userName, String englishName, String userTel, String firshMajor, String userEmail,
			String secondMajor, String profilePicture) {
		super();
		this.no = no;
		this.userName = userName;
		this.englishName = englishName;
		this.userTel = userTel;
		this.firshMajor = firshMajor;
		this.userEmail = userEmail;
		this.secondMajor = secondMajor;
		this.profilePicture = profilePicture;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getFirshMajor() {
		return firshMajor;
	}

	public void setFirshMajor(String firshMajor) {
		this.firshMajor = firshMajor;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getSecondMajor() {
		return secondMajor;
	}

	public void setSecondMajor(String secondMajor) {
		this.secondMajor = secondMajor;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	
}
