package com.lim.portfolio.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.lim.portfolio.model.dto.Portfolio;
import com.lim.portfolio.model.dto.PortfolioData;
import com.lim.portfolio.util.DBManager;

public class PortfolioDAO {
	
	public ArrayList<Portfolio> select() throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select no, title, leader, members, start_date, end_date, reg_date, (select count(*) from portfolio_data where portfolio_no = p.no) from portfolio p";
		ArrayList<Portfolio> list = new ArrayList<Portfolio>();
		
		try {
			con = DBManager.getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(new Portfolio(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), null, rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
			}
		} finally {
			DBManager.close(rs, stmt, con);
		}
		return list;
	} // end select everything
	
	public ArrayList<Portfolio> select(String title) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select no, title, leader, members, start_date, end_date, reg_date, (select count(*) from portfolio_data where portfolio_no = p.no) from portfolio p where title like ?";
		ArrayList<Portfolio> list = new ArrayList<Portfolio>();
		
		try {
			con = DBManager.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + title + "%");
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(new Portfolio(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), null, rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
			}
		} finally {
			DBManager.close(rs, stmt, con);
		}
		return list;
	} // end select for search
	
	public Portfolio select(int no) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select no, title, leader, members, content, start_date, end_date, reg_date from portfolio where no = ?";
		Portfolio p = null;
		
		try {
			con = DBManager.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			if (rs.next()) {
				p = new Portfolio(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), 0);
			}
		} finally {
			DBManager.close(rs, stmt, con);
		}
		return p;
	} // end select for one list
	
	public ArrayList<PortfolioData> selectDataList(int no) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select no, original_file_name, real_file_name from portfolio_data where portfolio_no = ?";
		ArrayList<PortfolioData> list = new ArrayList<PortfolioData>();
		
		try {
			con = DBManager.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(new PortfolioData(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		} finally {
			DBManager.close(rs, stmt, con);
		}
		return list;
	} // end select for dataList

	public void update(Portfolio p) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		String sql = "update portfolio set title=?, leader=?, members=?, content=?, start_date=STR_TO_DATE(?, '%Y-%m-%d'), end_date=STR_TO_DATE(?, '%Y-%m-%d'), reg_date=curdate()";
		
		try {
			con = DBManager.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getTitle());
			stmt.setString(2, p.getLeader());
			stmt.setString(3, p.getMember());
			stmt.setString(4, p.getContent());
			stmt.setString(5, p.getStartDate());
			stmt.setString(6, p.getEndDate());
			stmt.executeUpdate();
		} finally {
			DBManager.close(stmt, con);
		}
	} // end update
	
	public int insert(Portfolio p) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null, noStmt = null;
		ResultSet rs = null;
		String sql = "insert into portfolio(title, leader, members, content, start_date, end_date, reg_date) values(?, ?, ?, ?, STR_TO_DATE(?, '%Y-%m-%d'), STR_TO_DATE(?, '%Y-%m-%d'), curdate())";
		String noSql = "select last_insert_id() from portfolio";
		int no = 0;
		
		try {
			// controller에서 예외 처리해야 되서 걔속 던짐
			con = DBManager.getConnection();
			// 다른 insert막기. 트레디션을 해야 됨 > 커밋 or 롤백
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sql);
			noStmt = con.prepareStatement(noSql);
			stmt.setString(1, p.getTitle());
			stmt.setString(2, p.getLeader());
			stmt.setString(3, p.getMember());
			stmt.setString(4, p.getContent());
			// date는 String로 두면 알아서 처리해준다고 함
			stmt.setString(5, p.getStartDate());
			stmt.setString(6, p.getEndDate());
			// 매개변수 없는 애로 실행해야 됨. 왜냐면 벌써 우리가 다 해놨으니까
			// PreparedStatement는 특정 문만 가능 / Statement는 범용으로 사용
			stmt.executeUpdate();
			// 이걸 하고있는 사이에 다른 insert가 들어와 버리면 no가 잘못나올 수 있다. 
			// 이 두 작업을 다 마칠때까지 다른 insert가 들어오는 것을 막아야 한다.
			
			rs = noStmt.executeQuery();
			if(rs.next()) {
				no = rs.getInt(1);
			}
			// 커밋해줌. 만약 여기서 오류나면 롤백해줘야 됨
			con.commit();
		} catch (Exception e) {
			con.rollback();
			// throw exception 
			throw e;
		} finally {
			// 다시 오토커밋으로 돌려보냄. 안하면 괜찮다가 나중에 오류남 
			con.setAutoCommit(true);
			// 질 못되서 중간에 멈춰도 닫아는 줘야함
			DBManager.close(rs, stmt, con);
			DBManager.close(noStmt, con);
		}
		
		return no;
	} // end insert
	
	public void insert(int no, ArrayList<PortfolioData> list) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		String sql = "insert into portfolio_data(original_file_name, real_file_name, portfolio_no) values(?, ?, ?)";
		
		try {
			con = DBManager.getConnection();
			stmt = con.prepareStatement(sql);
			
			for (PortfolioData p : list) {
				stmt.setString(1, p.getOriginalFileName());
				stmt.setString(2, p.getRealFileName());
				stmt.setInt(3, no);
				stmt.executeUpdate();
			}
			
		} finally {
			DBManager.close(stmt, con);
		}
	} // end insert for data
	
	public void delete(int no) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		String sql = "delete from portfolio where no = ?";
		
		try {
			con = DBManager.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.executeUpdate();
		} finally {
			DBManager.close(stmt, con);
		}
	} // end delete

} // end class
