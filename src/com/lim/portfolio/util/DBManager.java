package com.lim.portfolio.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBManager {
	
	private static DataSource datasource;
	
	static {
		// 이 클래스가 메모리에 로드될 때 자동으로 한번만 실행하고 또다시 불리지 않는 것 >  굳이 매번 할 필요 없이 한번만 알면 되니까
		Context context;
		try {
			context = (Context) new InitialContext().lookup("java:comp/env/");
			datasource = (DataSource) context.lookup("jdbc/mysql");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws Exception {
		// connection하다가 문제가 발생하면 안되기 때문에 확인하기 위해 try, catch하지 않는다. > 대신에 throws로 던진다.
		return datasource.getConnection();
	}
	
	/**
	 * select 는 세 개다 필요
	 * @param rs
	 * @param stmt
	 * @param con
	 */
	public static void close(ResultSet rs, Statement stmt, Connection con) {

		try {
			if(rs != null ) { rs.close(); }
			if(stmt != null ) { stmt.close(); }
			if(con != null ) { con.close(); }
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * insert와 삭제는 두 개만 있으면 됨
	 * ResultSet에 null을 넣을 필요가 없음
	 * @param stmt
	 * @param con
	 */
	public static void close(Statement stmt, Connection con) {
		
		try {
			// select 는 세개다 필요
			if(stmt != null ) { stmt.close(); }
			if(con != null ) { con.close(); }
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
