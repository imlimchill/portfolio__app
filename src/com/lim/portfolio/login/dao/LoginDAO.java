package com.lim.portfolio.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lim.portfolio.login.dto.LoginInfo;
import com.lim.portfolio.login.dto.UserInfo;
import com.lim.portfolio.model.dto.Portfolio;
import com.lim.portfolio.util.DBManager;

public class LoginDAO {
	
	public int insert(LoginInfo l) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null, noStmt = null;
		ResultSet rs = null;
		String sql = "insert into user_login(user_id, user_password, user_email, register_date) values(?, ?, ?, curdate())";
		String noSql = "select last_insert_id() from into user_login";
		int no = 0;
		
		try {
			// controller에서 예외 처리해야 되서 걔속 던짐
			con = DBManager.getConnection();
			// 다른 insert막기. 트레디션을 해야 됨 > 커밋 or 롤백
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sql);
			noStmt = con.prepareStatement(noSql);
			stmt.setString(1, l.getUserId());
			stmt.setString(2, l.getUserPassword());
			stmt.setString(3, l.getUserEmail());
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
	
	
	public void insert(int no, UserInfo u) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		String sql = "insert into user_info(no, user_name, english_name, user_tel, firsh_major, user_email, second_major, profile_picture) values(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			con = DBManager.getConnection();
			stmt = con.prepareStatement(sql);

			stmt.setInt(1, no);
			stmt.setString(2, u.getUserName());
			stmt.setString(3, u.getEnglishName());
			stmt.setString(4, u.getUserTel());
			stmt.setString(5, u.getFirshMajor());
			stmt.setString(6, u.getUserEmail());
			stmt.setString(7, u.getSecondMajor());
			stmt.setString(8, u.getProfilePicture());
			
			stmt.executeUpdate();

		} finally {
			DBManager.close(stmt, con);
		}
	} // end insert for data
	
	public LoginInfo select(String id) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select no, user_id, user_password from user_login where user_id = ?";
		LoginInfo l = null;
		
		try {
			con = DBManager.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				l = new LoginInfo(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		} finally {
			DBManager.close(rs, stmt, con);
		}
		return l;
	} // end select for login
	
	public UserInfo selectUserInfo(int no) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select no, user_name, english_name, user_tel, firsh_major, user_email, second_major, profile_picture from user_info where no = ?";
		UserInfo u = null;
		
		try {
			con = DBManager.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			while(rs.next()) {
				u = new UserInfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8));
			}
		} finally {
			DBManager.close(rs, stmt, con);
		}
		return u;
	} // end select for user_info
}
