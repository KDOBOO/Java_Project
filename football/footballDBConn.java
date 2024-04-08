package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class footballDBConn {
	private static Connection conn;
	
	private footballDBConn() {
		
	}
	
	public static Connection getConnection() {
		//String url="jdbc:oracle:thin:@//아이피주소:포트번호/SID";
		//String url="jdbc:oracle:thin:@//211.238.142.84:1521/xe";
		String url="jdbc:oracle:thin:@//127.0.0.1:1521/xe";
		String user ="football";
		String pwd = "football23";
		
		if(conn==null) {
		try {
			//Oracle Driver 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Connection 객체 반환
			conn = DriverManager.getConnection(url,user,pwd);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		}
		return conn;
	}
	
	public static void close() {
		if (conn != null) {
			try {
				if(conn.isClosed() ) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		conn = null;
	}
}
