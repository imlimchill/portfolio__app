package com.lim.portfolio.model.service;

import java.util.ArrayList;

import com.lim.portfolio.model.dao.PortfolioDAO;
import com.lim.portfolio.model.dto.Portfolio;
import com.lim.portfolio.model.dto.PortfolioData;

public class PortfolioService {
	
	private PortfolioDAO pDAO = new PortfolioDAO();
	
	/**
	 * 삽입하기
	 * 함수의 문제 insert는 됐는데 register는 일부만 되거나 안될 수도 있음
	 * 원래 여기까지 트렌지션처리가 묶여야 되느데 일다 안하고 넘어감
	 * 코드가 너무 복잡해져서
	 * @param p
	 * @throws Exception
	 */
	public void register(Portfolio p) throws Exception {
		// insert가 되면 포트폴리오 번호가 리턴된다.
		int no = pDAO.insert(p);
		// 넘길 첨부파일이 있으면 넘긴다.
		ArrayList<PortfolioData> list = p.getDataList();
		if(list != null && list.size() > 0) {
			pDAO.insert(no, p.getDataList());
		}
	}
	
	/**
	 * 포트폴리오 자료 가져오기
	 * @return 
	 * @throws Exception 
	 */
	public ArrayList<Portfolio> getList() throws Exception {
		return pDAO.select();
	}
	
	/**
	 * 포트폴리오 타이플로 검색하기 
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Portfolio> search(String title) throws Exception {
		return pDAO.select(title);
	}
	
	/**
	 * 상세조회
	 * @throws Exception 
	 */
	public Portfolio getDetail(int no) throws Exception {
		Portfolio p = pDAO.select(no);
		p.setDataList(pDAO.selectDataList(no));
		
		return p;
	}
	
	public void modify(Portfolio p) throws Exception {
		pDAO.update(p);
	}
	
	public void remove(int no) throws Exception {
		pDAO.delete(no);
	}
	
} // end class
