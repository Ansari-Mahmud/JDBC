package com.mahmud.jlc;

import java.sql.*;

public class JDBCUtil {
	static {
		try {
			//1. Load the Driver
			Class.forName("org.postgresql.Driver");
			//Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("oracle:thin:@localhost:1521:XE");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getPostgresConnection() throws SQLException{
		String url = "jdbc:postgresql://localhost:5432/javaProgrmaming";
		Connection con = DriverManager.getConnection(url, "postgres","admin");
		return con;
	}
	public static Connection getOracleConnection() throws SQLException{
		String url = "jdb:oracle:thin:@localhost:1521/javaProgramming";
		Connection con = DriverManager.getConnection(url, "system","admin");
		return con;
	}
	public static void cleanup(Statement st, Connection con) {
		try {
			if(st != null)
				st.close();
			if(con != null)
				con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void cleanup(Statement st, Connection con, ResultSet rs) {
		try {
			if(st != null)
				st.close();
			if(rs != null)
				rs.close();
			if(con != null)
				con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

