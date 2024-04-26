package com.mahmud.jlc;

import java.sql.*;

public class Lab1 {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			//1. Load the Driver
			Class.forName("org.postgresql.Driver");
			//2. Establish the Connection
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/javaProgrmaming", "postgres", "admin");
			//3. Prepare the SQL Statement
			String sql ="insert into product values(3,'Grapes',30)";
			//4. Create the JDBC Statement
			st = con.createStatement();
			//5. Submit SQL Statement to Database using JDBC Statement
			int x = st.executeUpdate(sql);
			//6. Process the Result
			if( x ==1) {
				System.out.println("Inserted");
			}else {
				System.out.println("Not Inseted");
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
