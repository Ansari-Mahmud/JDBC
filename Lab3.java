package com.mahmud.jlc;

import java.sql.*;

public class Lab3 {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		try {
			con = JDBCUtil.getPostgresConnection();
			String sql ="insert into product values(4,'Pomegrante',60)";
			st = con.createStatement();
			int x = st.executeUpdate(sql);
			if( x ==1) {
				System.out.println("Inserted");
			}else {
				System.out.println("Not Inseted");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.cleanup(st, con);
		}
	}
}
