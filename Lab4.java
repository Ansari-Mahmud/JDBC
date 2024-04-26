package com.mahmud.jlc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Lab4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			con = JDBCUtil.getPostgresConnection();
			String sql ="select * from product";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while( rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int price = rs.getInt(3);
				System.out.println(id+"\t"+name+"\t"+price);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.cleanup(null, con, rs);
		}
	}

}
