package com.lim.portfolio.model.dto;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Portfolio {
	private int no;
	private String title;
	private String leader;
	private String member;
	private String content;
	private String startDate;
	private String endDate;
	private String regtDate;
	private ArrayList<PortfolioData> dataList;
	private int dataCount;
	private int memberCount;
	
	// constructor
	/**
	 * db에서 포트폴리오 내용을 가지고 올때 사용
	 * @param no
	 * @param title
	 * @param leader
	 * @param member
	 * @param content
	 * @param startDate
	 * @param endDate
	 * @param regtDate
	 * @param dataCount
	 */
	public Portfolio(int no, String title, String leader, String member, String content, String startDate,
			String endDate, String regtDate, int dataCount) {
		this(title, leader, member, content, startDate, endDate);
		this.no = no;
		this.regtDate = regtDate;
		this.dataCount = dataCount;
	}
	
	/**
	 * db에 등록할 때 사용할 생성자
	 * @param title
	 * @param leader
	 * @param member
	 * @param content
	 * @param startDate
	 * @param endDate
	 */
	public Portfolio(String title, String leader, String member, String content, String startDate, String endDate) {
		super();
		this.title = title;
		this.leader = leader;
		this.member = member;
		this.content = content;
		this.startDate = startDate;
		this.endDate = endDate;
		StringTokenizer st = new StringTokenizer(member, ",");
		this.memberCount = st.countTokens();
	}

	// getter, setter
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRegtDate() {
		return regtDate;
	}

	public void setRegtDate(String regtDate) {
		this.regtDate = regtDate;
	}

	public ArrayList<PortfolioData> getDataList() {
		return dataList;
	}

	public void setDataList(ArrayList<PortfolioData> dataList) {
		this.dataList = dataList;
	}

	public int getDataCount() {
		return dataCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}
	
}
