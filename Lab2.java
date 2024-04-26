package com.mahmud.jlc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Lab2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			//1. Load the Driver
			//Class.forName("org.postgresql.Driver");
			//2. Establish the Connection
			con=DriverManager.getConnection("jdbc:postgres://localhost:5432/java", "postgres", "admin");
			//3. Prepare the SQL Statement
			String sql ="select * from product";
			//4. Create the JDBC Statement
			st = con.createStatement();
			//5. Submit SQL Statement to Database using JDBC Statement
			rs = st.executeQuery(sql);
			//6. Process the Result
			while( rs.next()) {
				int id = rs.getInt(0);
				String name = rs.getString(1);
				int price = rs.getInt(2);
				System.out.println(id+"\t"+name+"\t"+price);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//7. Close the Resources
		finally {
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

}
