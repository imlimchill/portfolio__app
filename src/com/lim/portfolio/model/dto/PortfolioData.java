package com.lim.portfolio.model.dto;

public class PortfolioData {
	private int no;
	private String originalFileName;
	private String realFileName;
	// 이건 포트폴리오에 나주에 추가할 걀우에는 추가 함
	private int portfolioNo;
	
	// constructor
	public PortfolioData(int no, String originalFileName, String realFileName, int portfolioNo) {
		super();
		this.no = no;
		this.originalFileName = originalFileName;
		this.realFileName = realFileName;
		this.portfolioNo = portfolioNo;
	}

	public PortfolioData(int no, String originalFileName, String realFileName) {
		super();
		this.no = no;
		this.originalFileName = originalFileName;
		this.realFileName = realFileName;
	}

	public PortfolioData(String originalFileName, String realFileName) {
		super();
		this.originalFileName = originalFileName;
		this.realFileName = realFileName;
	}

	// getter, setter
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getRealFileName() {
		return realFileName;
	}

	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}

	public int getPortfolioNo() {
		return portfolioNo;
	}

	public void setPortfolioNo(int portfolioNo) {
		this.portfolioNo = portfolioNo;
	}
	
	
}
